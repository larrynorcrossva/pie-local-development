package gov.va.pie.nvappmscdw.service;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.common.utils.CommonUtils;
import gov.va.pie.nvappmscdw.model.VistaPharmacyResponse;
import gov.va.pie.nvappmscdw.model.VistaPharmacyResponseContext;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

@Service
public class GetDataFromPPMS<T> {
	
	private static final Logger LOG = LogManager.getLogger(GetDataFromPPMS.class);
	
	private static final String JSON = "JSON";

	
	@Value("${vista.nva.save.local}")
	private boolean saveToLocalFiles;
	
	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	private EntityManagerFactory emf;
	
	@Autowired
	private DataSource ds;
	
	@Autowired
	private PieErrorService pieErrorService;
	
	@Autowired
	private PieLogService pieLogService;
	
	public void getDataFromFile (
			String folderPath, 
			String cleanStagingQuery, 
			Function<VistaPharmacyResponse, List<T>> getReturnListFunc,
			Consumer<List<T>> populateStageFunc,
			String movingStagingQuery,
			String logHeader) throws IOException, PersistenceException, IllegalStateException {
		
		try {
			java.util.Date time = new java.util.Date();
		
			File dataFolder = new File(folderPath);
			
			int totalRecourdsCount = 0;
			
			if (dataFolder.exists()) {
				NvaUtils.runProcedureInTransaction(entityManager, cleanStagingQuery, ds);
				
				LOG.info ("Enter the folder: " + folderPath);
	
				for (final File fileEntry : dataFolder.listFiles()) {
					List<T> dataList = null;
					if (!fileEntry.isDirectory()) {
						String fileName = fileEntry.getName();
						if ((fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length())
								.toUpperCase(Locale.ENGLISH).equals(JSON))) {
							VistaPharmacyResponseContext vistaPharmacyResponseContext = CommonUtils.createModelFromJSONFile(fileEntry,
									VistaPharmacyResponseContext.class);
							if (vistaPharmacyResponseContext != null) {
								List<VistaPharmacyResponse> vistaPharmacyResponses = vistaPharmacyResponseContext.getResponses();
								if ((vistaPharmacyResponses != null) && (vistaPharmacyResponses.get(0) != null)) {
									dataList = getReturnListFunc.apply(vistaPharmacyResponses.get(0));
									pieLogService.recordMessage(logHeader + " file name: " + fileName + " contains total record count: " + dataList.size());
									populateStageFunc.accept(dataList);
									
									totalRecourdsCount = totalRecourdsCount + dataList.size();
								}
							}
						}
					}
				}
				
				moveFromStatingToTables(movingStagingQuery);
				LOG.info(" Time took for processing " + totalRecourdsCount + " " + logHeader 
						+ " " + (new java.util.Date().getTime() - time.getTime()) / 1000 / 60 + " mins");
				
			}
			else {
				LOG.info("Bulk files: " + folderPath + " not exists");
			}
		} catch (IOException | PersistenceException | IllegalStateException e) {
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			pieErrorService.captureError(e, App.VISTA);
			throw new IOException(e.getMessage(), e);
		}
	}

	public Boolean getDataFromWebService (
			Date providerModifiedOnOrAfterDateParameter,
			Function<VistaPharmacyResponse, List<T>> getReturnListFunc,
			Function<Void, Integer> getTotalPage,
			Function<Integer, VistaPharmacyResponseContext> getVistaResponseByPageNumber,
			BiConsumer<VistaPharmacyResponseContext, Integer> saveVistaResponseToFile,
			Consumer<List<T>> populateStagingFunc,
			String movingStagingQuery,
			String logHeader,
			Integer startOnPage) {
		
		Boolean ret = false;
		try {
			int size= getDateTFromPPMS(
					providerModifiedOnOrAfterDateParameter,
					getTotalPage,
					getVistaResponseByPageNumber,
					getReturnListFunc,
					saveVistaResponseToFile,
					logHeader,
					populateStagingFunc,
					startOnPage
					
					);
			
			LOG.info("Total number of " + logHeader + " records -->" + size);
			if (!saveToLocalFiles) {
				//populateStagingFunc.accept(dataList);
				moveFromStatingToTables(movingStagingQuery);
			}
			ret = true;

		} catch (RestClientException e) {
			LOG.error("Call to PPMS to get " + logHeader + " for modifiedOnOrAfterDate, "
					+ providerModifiedOnOrAfterDateParameter + " failed " + e.getMessage(), e);
		}
		
		return ret;
		
	}
	
	private int getDateTFromPPMS(
			Date modifiedOnOrAfterDateParameter,
			Function<Void, Integer> getVistaPharmacyResponseTotalPages,
			Function<Integer, VistaPharmacyResponseContext> getVistaResponseByPageNumber,
			Function<VistaPharmacyResponse, List<T>> getReturnListFunc,
			BiConsumer<VistaPharmacyResponseContext, Integer> saveVistaResponseToFile,
			String logHeader,
			Consumer<List<T>> populateStagingFunc,
			Integer startOnPage
			)
			throws RestClientException {
		
		Integer pageNumber = 1;
		if (startOnPage != null && startOnPage > 0) {
			pageNumber = startOnPage;
		}
		
		Integer totalPages;
		List<T> dataListList = new ArrayList<T>();
		int count=0;
		try { 
			// get total number of pages/records
			LOG.info("Calling PPMS to get " + logHeader  + " for modifiedOnOrAfterDate, " + modifiedOnOrAfterDateParameter);
			totalPages = getVistaPharmacyResponseTotalPages.apply(null);
			// get data if pages are returned
			if (totalPages > 0) {
				LOG.info("Total pages returned for " + logHeader + " data from PPMS, " + totalPages);
				
				while (pageNumber <= totalPages) {
					dataListList.clear();
					LOG.info("Get " + logHeader + " data for page number, " + pageNumber);
					VistaPharmacyResponseContext responseContext = getVistaResponseByPageNumber.apply(pageNumber);
					if (responseContext != null) {
						// get response object if context is not null
						if (responseContext.getResponses() != null) {
							VistaPharmacyResponse response = responseContext.getResponses().get(0);
							// get providers
							if (response != null) {
								dataListList.addAll(getReturnListFunc.apply(response));
								if (saveToLocalFiles) {
									saveVistaResponseToFile.accept(responseContext, pageNumber);
								}else {
									populateStagingFunc.accept(dataListList);
									count=count+dataListList.size();
								}
							}
						}
					}
					LOG.info(" Memory after page : "+pageNumber);
					pageNumber++;
				}
			}
		}  catch (RestClientException e) {
			pieErrorService.capturePPMSUpdateError(e, App.VISTA, pageNumber);
			throw new RestClientException(e.getMessage(), e);
		}
		return count;
	}

	private void moveFromStatingToTables(String procedure) throws PersistenceException, IllegalStateException {
        LOG.info("Moving data from Staging for " + procedure);
        try {
	        if(ds!=null) 
	        {
	               LOG.info("Before " + procedure + " Active: " + ds.getNumActive()+" "+"Idle: " + ds.getNumIdle());
	        }
	        EntityManager emLocal = emf.createEntityManager();
	        EntityTransaction transaction = emLocal.getTransaction();
	        transaction.begin();
	        StoredProcedureQuery storedProcedureQuery = emLocal.createStoredProcedureQuery("App." + CommonConstants.DB_ENV + procedure);
	        storedProcedureQuery.executeUpdate();
	        transaction.commit();
	        emLocal.close();
	        if(ds!=null) 
	        {
	               LOG.info("After calling " + procedure + " moveFromStatingToTables Active: " + ds.getNumActive()+" "+"Idle: " + ds.getNumIdle());
	        }
	        
	        LOG.info("Done Moving data from Staging for " + procedure);
        } catch (PersistenceException | IllegalStateException e) {
			
			LOG.error(e.getMessage());
			pieErrorService.captureError(e, App.VISTA);
		}
	}

	

}
