package gov.va.pie.nvappms.cdw.serviceactivator;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.entities.PPMSNonVAInboundResponse;
import gov.va.pie.common.repositories.PPMSNonVAInboundResponseRepository;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.common.utils.CommonUtils;
import gov.va.pie.nvappms.cdw.exception.CDWResponseErrorHandler;
import gov.va.pie.nvappms.cdw.model.CDWAgreement;
import gov.va.pie.nvappms.cdw.model.CDWCareSite;
import gov.va.pie.nvappms.cdw.model.CDWProvider;
import gov.va.pie.nvappms.cdw.model.CDWProviderService;
import gov.va.pie.nvappms.cdw.model.CDWResponse;
import gov.va.pie.nvappms.cdw.model.CDWResponseContext;
import gov.va.pie.nvappms.cdw.service.CDWAgreementService;
import gov.va.pie.nvappms.cdw.service.CDWCareSiteService;
import gov.va.pie.nvappms.cdw.service.CDWNVAProviderService;
import gov.va.pie.nvappms.cdw.service.CDWProviderServicesService;
import gov.va.pie.nvappms.cdw.utils.CDWUtils;

@Component("nvaPpmsCdwActivator")
public class NvaPpmsCdwActivator {

	static final Logger LOG = LogManager.getLogger(NvaPpmsCdwActivator.class);

	private static final String MODIFIED_ON_AFTER_DATE = "modifiedOnOrAfterDate";

	private static final String EXPAND = "$expand";

	private static final String PAGE_PROVIDERS = "providersPage";

	private static final String EXPAND_PROVIDERS = "providers";

	private static final String PAGE_PROVIDER_SERVICES = "providerServicesPage";

	private static final String EXPAND_PROVIDER_SERVICES = "providerservices";

	private static final String PAGE_AGREEMENTS = "agreementsPage";

	private static final String EXPAND_AGREEMENTS = "agreements";

	private static final String PAGE_CARE_SITES = "careSitesPage";

	private static final String EXPAND_CARE_SITES = "caresites";

	private static final String PROCEDURE_PROVIDER_SERVICE = "App." + CommonConstants.DB_ENV + "merge_ProviderService";
	private static final String PROCEDURE_PROVIDERS = "App." + CommonConstants.DB_ENV + "merge_NonVAProvider";
	private static final String PROCEDURE_AGREEMENT = "App." + CommonConstants.DB_ENV + "merge_ProviderAgreement";
	private static final String PROCEDURE_CARE_SITE = "App." + CommonConstants.DB_ENV + "merge_Caresite";

	private RestTemplate restTemplate;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Autowired
	private DataSource dataSource;

	@Value("${ppms.cdw.provider.endpoint}")
	private String ppmsCdwProviderEndpoint;

	@Value("${ppms.cdw.providerservice.endpoint}")
	private String ppmsCdwProviderServiceEndpoint;

	@Value("${ppms.cdw.agreement.endpoint}")
	private String ppmsCdwAgreementEndpoint;

	@Value("${ppms.cdw.caresite.endpoint}")
	private String ppmsCdwCareSiteEndpoint;

	@Value("${cdw.nva.save.local}")
	private boolean saveToLocalFiles;

	@Value("${cdw.bulk.file.location}")
	private String cdwBulkFileLocation;

	@Value("${cdw.manual.directory}")
	private String cdwManualDirectory;
	
	@Value("${cdw.provider.services.start.page}")
	private Integer providerServicesStartPage;
	
	@Value("${cdw.care.site.start.page}")
	private Integer careSiteStartPage;

	@Autowired
	private CDWNVAProviderService cdwNvaProviderService;

	@Autowired
	private CDWAgreementService cdwAgreementService;

	@Autowired
	private CDWCareSiteService cdwCareSiteService;

	@Autowired
	private CDWProviderServicesService cdwProviderServicesService;

	@Autowired
	private PPMSNonVAInboundResponseRepository ppmsNonVAInboundResponseRepo;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private PieErrorService pieErrorService;

	@Autowired
	private RetryTemplate retryTemplate;
	
	@Autowired
	private PieLogService pieLogService;

	private CDWResponseContext cdwResponseContext;

	private List<CDWResponse> cdwResponses;

	List<CDWProvider> cdwProviderList = new ArrayList<>();

	List<CDWAgreement> agreementModels = new ArrayList<CDWAgreement>();

	List<CDWCareSite> careSiteModels = new ArrayList<CDWCareSite>();

	List<CDWProviderService> providerServiceModels = new ArrayList<CDWProviderService>();

	private static final String JSON = "JSON";

	private final String CDWTransactionType = "CDW";
	private final String CDWManualResponseMesssageText = "All extraction files are processed";
	private final String CDWDailyResponseMessageText = "All services pulled data";
	private final String CDWManualCreatedBy = "ManualLoad";

	@ServiceActivator
	@Async

	public void processPPMSData(Message<String> processMessage) throws IOException, PersistenceException, SQLException {
		MessageHeaders headers = processMessage.getHeaders();
		String urlString = (String) headers.get("http_requestUrl");
		if (urlString.contains("mode=manual")) {
			
			MultiValueMap<String, String> parameters = UriComponentsBuilder.fromUriString(urlString).build()
					.getQueryParams();
			List<String> dirParam = parameters.get(cdwManualDirectory);
			if (dirParam != null) {
				String dirLocation = dirParam.get(0);
				if (dirLocation != null && !dirLocation.isEmpty()) {
					LOG.info("Bulk file location: " + cdwBulkFileLocation + File.separator + dirLocation);
					manualDataLoadDataFromFiles(dirLocation);
				}
			} else {
				LOG.error("Missing directory parameter in the request!");
				pieErrorService.captureError(new IOException("Missing directory parameter in the request!"), App.CDW);
				 throw new IOException("Missing directory parameter in the request!");
			}
			
		} else {
			pullPPMSDataUpdates();
		}
	}

	@Transactional
	public void manualDataLoadDataFromFiles(String lastDateOfDataLoad) throws IOException, PersistenceException, SQLException {
		try {
			LOG.info("Processing data from files");
			File dataDirectory = new File(cdwBulkFileLocation + File.separator + lastDateOfDataLoad);
			if (!dataDirectory.exists()) {
				LOG.info(dataDirectory.getAbsolutePath() + " directory doesn't exists");
				throw new IOException(dataDirectory.getAbsolutePath() + " directory doesn't exists");
			}
	
			Date lastDayOfManualData = Date.valueOf(lastDateOfDataLoad);
	
			java.util.Date startDate = new java.util.Date();
			File dataFolder = new File(
					cdwBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + CDWUtils.PROVIDERS_DIR);
			if (dataFolder.exists()) {
				for (final File fileEntry : dataFolder.listFiles()) {
					if (!fileEntry.isDirectory()) {
						String fileName = fileEntry.getName();
						if ((fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length())
								.toUpperCase(Locale.ENGLISH).equals(JSON))) {
							pieLogService.recordMessage("Processing Provider file: " + fileName);
							cdwResponseContext = CommonUtils.createModelFromJSONFile(fileEntry, CDWResponseContext.class);
							if (cdwResponseContext != null) {
								cdwResponses = cdwResponseContext.getResponses();
								if ((cdwResponses != null) && (cdwResponses.get(0) != null)) {
									cdwProviderList = ((CDWResponse) cdwResponses.get(0)).getCdwProviders();
									cdwNvaProviderService.processCDWNVAProviders(cdwProviderList);
								}
							} else {
								pieLogService.recordMessage("*** Could Not parse file " + fileName + " ***");
							}
						}
					}
				}
				StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(PROCEDURE_PROVIDERS);
				storedProcedureQuery.execute();
				entityManager.close();
			}
	
			dataFolder = new File(
					cdwBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + CDWUtils.AGREEMENTS_DIR);
			if (dataFolder.exists() && dataFolder.listFiles() != null) {
				for (final File fileEntry : dataFolder.listFiles()) {
					if (!fileEntry.isDirectory()) {
						String fileName = fileEntry.getName();
						if ((fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length())
								.toUpperCase(Locale.ENGLISH).equals(JSON))) {
							pieLogService.recordMessage("Processing Agreement file: " + fileName);
							cdwResponseContext = CommonUtils.createModelFromJSONFile(fileEntry, CDWResponseContext.class);
							if (cdwResponseContext != null) {
								cdwResponses = cdwResponseContext.getResponses();
								if (cdwResponses != null && cdwResponses.get(0) != null) {
									agreementModels = cdwResponses.get(0).getAgreements();
									cdwAgreementService.processAgreements(agreementModels);
								}
							} else {
								pieLogService.recordMessage("Processing Agreement file: " + fileName);
							}
						}
					}
				}
				StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery(PROCEDURE_AGREEMENT);
				storedProcedureQuery.execute();
				entityManager.close();
			} else {
				LOG.info("Agreements folder not found");
			}
	
			dataFolder = new File(
					cdwBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + CDWUtils.CARE_SITES_DIR);
			if (dataFolder.exists() && dataFolder.listFiles() != null) {
				for (final File fileEntry : dataFolder.listFiles()) {
					if (!fileEntry.isDirectory()) {
						String fileName = fileEntry.getName();
						if ((fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length())
								.toUpperCase(Locale.ENGLISH).equals(JSON))) {
							pieLogService.recordMessage("Processing CareSite file: " + fileName);
							cdwResponseContext = CommonUtils.createModelFromJSONFile(fileEntry, CDWResponseContext.class);
							if (cdwResponseContext != null) {
								cdwResponses = cdwResponseContext.getResponses();
								if (cdwResponses != null && cdwResponses.get(0) != null) {
									careSiteModels = cdwResponses.get(0).getCareSites();
									cdwCareSiteService.processCareSites(careSiteModels/* , nonVAProviderNPIToIdMap */);
								}
							} else {
								pieLogService.recordMessage("*** Could Not parse file " + fileName + " ***");
							}
						}
					}
					StoredProcedureQuery storedProcedureQuery = entityManager
							.createStoredProcedureQuery(PROCEDURE_CARE_SITE);
					storedProcedureQuery.execute();
					entityManager.close();
				}
			} else {
				LOG.info("Care Sites folder not found");
			}
	
			dataFolder = new File(cdwBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator
					+ CDWUtils.PROVIDER_SERVICE_DIR);
			if (dataFolder.exists() && dataFolder.listFiles() != null) {
				for (final File fileEntry : dataFolder.listFiles()) {
					String fileName = fileEntry.getName();
					if ((fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length()).toUpperCase(Locale.ENGLISH)
							.equals(JSON))) {
						pieLogService.recordMessage("Processing Provider Service file: " + fileName);
						cdwResponseContext = CommonUtils.createModelFromJSONFile(fileEntry, CDWResponseContext.class);
						if (cdwResponseContext != null) {
							cdwResponses = cdwResponseContext.getResponses();
							if (cdwResponses != null && cdwResponses.get(0) != null) {
								providerServiceModels = cdwResponses.get(0).getProviderServices();
								cdwProviderServicesService.processProviderServices(providerServiceModels);
							}
						} else {
							pieLogService.recordMessage("*** Could Not parse file " + fileName + " ***");
						}
					}
				}
				StoredProcedureQuery storedProcedureQuery = entityManager
						.createStoredProcedureQuery(PROCEDURE_PROVIDER_SERVICE);
				storedProcedureQuery.execute();
				entityManager.close();
			} else {
				LOG.info("Provider Service folder not found");
			}
	
			// Insert a success record to "PPMSNonVAInboundResponse"
			PPMSNonVAInboundResponse vistaPPMSNonVAInboundResponse = new PPMSNonVAInboundResponse();
	
			vistaPPMSNonVAInboundResponse.setTransactionNumber(UUID.randomUUID().toString());
			vistaPPMSNonVAInboundResponse.setTransactionCount(0);
			vistaPPMSNonVAInboundResponse.setTransactionType(CDWTransactionType);
			vistaPPMSNonVAInboundResponse.setProviderLastSuccessDate(lastDayOfManualData);
			vistaPPMSNonVAInboundResponse.setIsProviderFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setProviderServiceLastSuccessDate(lastDayOfManualData);
			vistaPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setIsCareSiteFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setCareSiteLastSuccess_Date(lastDayOfManualData);
			vistaPPMSNonVAInboundResponse.setIsProviderAgreementFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setProviderAgreementLastSuccessDate(lastDayOfManualData);
			vistaPPMSNonVAInboundResponse.setCreatedDate((new Timestamp((new java.util.Date()).getTime())));
			vistaPPMSNonVAInboundResponse.setModifiedDate(null);
			vistaPPMSNonVAInboundResponse.setModifiedBy(null);
			vistaPPMSNonVAInboundResponse.setPPMSNonVAInboundResponseId(null);
			vistaPPMSNonVAInboundResponse.setResponseMessageText(CDWManualResponseMesssageText);
			vistaPPMSNonVAInboundResponse.setCreatedBy(CDWManualCreatedBy);
			ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
	
			java.util.Date endDate = new java.util.Date();
			LOG.info("Finished Processing data from files in " + (endDate.getTime() - startDate.getTime()) + " MS");
			LOG.info("Manual CDW file processing is done for the end day: " + lastDateOfDataLoad);
		} catch (IOException | PersistenceException | SQLException e) {
			
			LOG.error(e.getMessage());
			pieErrorService.captureError(e, App.CDW);
			throw new IOException(e.getMessage(), e);
		}
	}

	public void moveFromStatingToTables(String procedure, DataSource dataSource) throws PersistenceException, IllegalStateException {
		try {
			LOG.info("Moving data from Staging for " + procedure);
			if (dataSource != null) {
				LOG.info("Before " + procedure + " Active: " + dataSource.getNumActive() + " " + "Idle: "
						+ dataSource.getNumIdle());
			}
			EntityManager emLocal = emf.createEntityManager();
			EntityTransaction transaction = emLocal.getTransaction();
			transaction.begin();
			StoredProcedureQuery storedProcedureQuery = emLocal
					.createStoredProcedureQuery(procedure);
			storedProcedureQuery.executeUpdate();
			transaction.commit();
			emLocal.close();
			if (dataSource != null) {
				LOG.info("After calling " + procedure + " moveFromStatingToTables Active: " + dataSource.getNumActive()
						+ " " + "Idle: " + dataSource.getNumIdle());
			}
	
			LOG.info("Done Moving data from Staging for " + procedure);
		} catch (PersistenceException | IllegalStateException e) {
			
			LOG.error(e.getMessage());
			pieErrorService.captureError(e, App.CDW);
			throw new RestClientException(e.getMessage(), e);
		}
	}

	public void pullPPMSDataUpdates() {

		LOG.info("Processing data from web calls");
		Date todayForModifiedOnOrAfterDateParameter = new Date(new java.util.Date().getTime());

		Date providerModifiedOnOrAfterDateParameter = todayForModifiedOnOrAfterDateParameter;

		PPMSNonVAInboundResponse cdwPPMSNonVAInboundResponse = ppmsNonVAInboundResponseRepo
				.findlatestResonse(CommonConstants.PPMSNONVAINBOUNDRESPONSE_CDW);

		if (cdwPPMSNonVAInboundResponse == null || (cdwPPMSNonVAInboundResponse.getProviderLastSuccessDate() == null)) {
			LOG.info(CDWUtils.INITIALIZATION_FAILURE);
			return;
		} else {
			providerModifiedOnOrAfterDateParameter = cdwPPMSNonVAInboundResponse.getProviderLastSuccessDate();
		}

		restTemplate = restTemplateBuilder.errorHandler(new CDWResponseErrorHandler()).build();

		// get providers
		try {
			getCDWProvidersFromPPMS(ppmsCdwProviderEndpoint, providerModifiedOnOrAfterDateParameter);

			if (!saveToLocalFiles) {
				// cdwNvaProviderService.processCDWNVAProviders(cdwProviderList);
			}

			if (dataSource != null) {
				LOG.info("Before calling EM NvaPpmsCdwActivator - getCDWProvidersFromPPMS -  Max Idle: "
						+ dataSource.getMaxIdle() + " " + "Max Active: " + dataSource.getMaxActive());
				LOG.info("Before  calling EM CommonUtils Active: " + dataSource.getNumActive() + " " + "Idle: "
						+ dataSource.getNumIdle());

			}
			cdwPPMSNonVAInboundResponse.setIsProviderFail(Boolean.FALSE);
			LOG.info("Before merging " + PROCEDURE_PROVIDERS);
			moveFromStatingToTables(PROCEDURE_PROVIDERS, dataSource);

			if (dataSource != null) {
				LOG.info("After calling EM NvaPpmsCdwActivator - getCDWProvidersFromPPMS -  Max Idle: "
						+ dataSource.getMaxIdle() + " " + "Max Active: " + dataSource.getMaxActive());
				LOG.info("After  calling EM CommonUtils Active: " + dataSource.getNumActive() + " " + "Idle: "
						+ dataSource.getNumIdle());

			}

		} catch (RestClientException e) {

			LOG.error("****** Error processing Providers ******  " + e.getMessage(), e);
			
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			cdwPPMSNonVAInboundResponse.setTransactionCount(cdwPPMSNonVAInboundResponse.getTransactionCount() + 1);
			cdwPPMSNonVAInboundResponse.setIsProviderFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setIsProviderAgreementFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setIsCareSiteFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PIE);
			cdwPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(cdwPPMSNonVAInboundResponse);
			return;
		}

		// get agreements
		try {
			getCDWAgreementsFromPPMS(ppmsCdwAgreementEndpoint, providerModifiedOnOrAfterDateParameter);
			if (!saveToLocalFiles) {
				// cdwAgreementService.processAgreements(agreementModels);
			}
			cdwPPMSNonVAInboundResponse.setIsProviderAgreementFail(Boolean.FALSE);
			cdwPPMSNonVAInboundResponse.setProviderAgreementLastSuccessDate(new Date(new java.util.Date().getTime()));
			if (dataSource != null) {
				LOG.info("Before calling EM NvaPpmsCdwActivator - getCDWAgreementsFromPPMS -  Max Idle: "
						+ dataSource.getMaxIdle() + " " + "Max Active: " + dataSource.getMaxActive());
				LOG.info("Before  calling EM CommonUtils Active: " + dataSource.getNumActive() + " " + "Idle: "
						+ dataSource.getNumIdle());

			}
			LOG.info("Before merging " + PROCEDURE_AGREEMENT);
			// StoredProcedureQuery storedProcedureQuery =
			// entityManager.createStoredProcedureQuery(PROCEDURE_AGREEMENT);
			// storedProcedureQuery.execute();
			moveFromStatingToTables(PROCEDURE_AGREEMENT, dataSource);

			if (dataSource != null) {
				LOG.info("After calling EM NvaPpmsCdwActivator - getCDWAgreementsFromPPMS -  Max Idle: "
						+ dataSource.getMaxIdle() + " " + "Max Active: " + dataSource.getMaxActive());
				LOG.info("After  calling EM CommonUtils Active: " + dataSource.getNumActive() + " " + "Idle: "
						+ dataSource.getNumIdle());

			}
		} catch (RestClientException e) {
			LOG.error("****** Error processing Agreements ******  " + e.getMessage(), e);
			
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			cdwPPMSNonVAInboundResponse.setTransactionCount(cdwPPMSNonVAInboundResponse.getTransactionCount() + 1);
			cdwPPMSNonVAInboundResponse.setIsProviderAgreementFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setIsCareSiteFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PIE);
			cdwPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(cdwPPMSNonVAInboundResponse);
			return;
		}

		// get care sites
		try {
			getCDWCareSitesFromPPMS(ppmsCdwCareSiteEndpoint, providerModifiedOnOrAfterDateParameter, careSiteStartPage);

			if (!saveToLocalFiles) {
				// cdwCareSiteService.processCareSites(careSiteModels);
			}

			if (dataSource != null) {
				LOG.info("Before calling EM NvaPpmsCdwActivator - getCDWCareSitesFromPPMS -  Max Idle: "
						+ dataSource.getMaxIdle() + " " + "Max Active: " + dataSource.getMaxActive());
				LOG.info("Before  calling EM CommonUtils Active: " + dataSource.getNumActive() + " " + "Idle: "
						+ dataSource.getNumIdle());

			}
			cdwPPMSNonVAInboundResponse.setCareSiteFail(Boolean.FALSE);
			cdwPPMSNonVAInboundResponse.setCareSiteLastSuccess_Date(new Date(new java.util.Date().getTime()));
			LOG.info("Before merging " + PROCEDURE_CARE_SITE);
			// StoredProcedureQuery storedProcedureQuery =
			// entityManager.createStoredProcedureQuery(PROCEDURE_CARE_SITE);
			moveFromStatingToTables(PROCEDURE_CARE_SITE, dataSource);

			if (dataSource != null) {
				LOG.info("After calling EM NvaPpmsCdwActivator - getCDWCareSitesFromPPMS -  Max Idle: "
						+ dataSource.getMaxIdle() + " " + "Max Active: " + dataSource.getMaxActive());
				LOG.info("After  calling EM CommonUtils Active: " + dataSource.getNumActive() + " " + "Idle: "
						+ dataSource.getNumIdle());

			}

		} catch (RestClientException e) {
			LOG.error("****** Error processing Care Sites ******  " + e.getMessage(), e);
			
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			cdwPPMSNonVAInboundResponse.setTransactionCount(cdwPPMSNonVAInboundResponse.getTransactionCount() + 1);
			cdwPPMSNonVAInboundResponse.setCareSiteFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PIE);
			cdwPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(cdwPPMSNonVAInboundResponse);
			return;
		}

		// get providerServices
		try {
			getCDWProviderServicesFromPPMS(ppmsCdwProviderServiceEndpoint, providerModifiedOnOrAfterDateParameter, providerServicesStartPage);

			if (!saveToLocalFiles) {
				// cdwProviderServicesService.processProviderServices(providerServiceModels);
			}
			cdwPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.FALSE);
			cdwPPMSNonVAInboundResponse.setProviderServiceLastSuccessDate(new Date(new java.util.Date().getTime()));
			LOG.info("Before merging " + PROCEDURE_PROVIDER_SERVICE);
			// StoredProcedureQuery storedProcedureQuery =
			// entityManager.createStoredProcedureQuery(PROCEDURE_PROVIDER_SERVICE);
			// storedProcedureQuery.execute();
			moveFromStatingToTables(PROCEDURE_PROVIDER_SERVICE, dataSource);

		} catch (RestClientException e) {
			LOG.error("****** Error processing Provider Services ******  " + e.getMessage(), e);
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			cdwPPMSNonVAInboundResponse.setTransactionCount(cdwPPMSNonVAInboundResponse.getTransactionCount() + 1);
			cdwPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.TRUE);
			cdwPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PIE);
			cdwPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(cdwPPMSNonVAInboundResponse);
			return;
		}

		entityManager.detach(cdwPPMSNonVAInboundResponse);
		cdwPPMSNonVAInboundResponse.setTransactionNumber(UUID.randomUUID().toString());
		cdwPPMSNonVAInboundResponse.setTransactionCount(0);
		cdwPPMSNonVAInboundResponse.setResponseMessageText(CDWDailyResponseMessageText);
		cdwPPMSNonVAInboundResponse.setCreatedDate(new Timestamp((new java.util.Date()).getTime()));
		cdwPPMSNonVAInboundResponse.setCreatedBy(CommonConstants.PIE);
		cdwPPMSNonVAInboundResponse.setModifiedDate(null);
		cdwPPMSNonVAInboundResponse.setModifiedBy(null);
		cdwPPMSNonVAInboundResponse.setPPMSNonVAInboundResponseId(null);
		cdwPPMSNonVAInboundResponse.setProviderLastSuccessDate(new Date(new java.util.Date().getTime()));
		ppmsNonVAInboundResponseRepo.save(cdwPPMSNonVAInboundResponse);
		LOG.info("***** Successfull Processed all PPMS rest calls ******");
		

		ppmsNonVAInboundResponseRepo.save(cdwPPMSNonVAInboundResponse);
		LOG.info("Finished Processing data from web calls");

	}

	/**
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDate
	 * @return
	 * @throws RestClientException
	 */
	public void getCDWProvidersFromPPMS(String requestURL, Date modifiedOnOrAfterDate) throws RestClientException {
		int pageNumber = 1;
		int totalPages;
		List<CDWProvider> providerList = new ArrayList<CDWProvider>();

		try {
			LOG.info("Calling PPMS to get CDW Providers for modifiedOnOrAfterDate, " + modifiedOnOrAfterDate);
			totalPages = getCDWResponseTotalPages(requestURL, modifiedOnOrAfterDate, PAGE_PROVIDERS, pageNumber);
	
			if (totalPages > 0) {
				LOG.info("Total pages returned for Providers data from PPMS, " + totalPages);
				while (pageNumber <= totalPages) {
					providerList.clear();
					LOG.info("Get Providers data for page number, " + pageNumber);
					CDWResponseContext responseContext = getCDWResponseByPageNumber(requestURL, modifiedOnOrAfterDate,
							PAGE_PROVIDERS, pageNumber, EXPAND_PROVIDERS);
					if (responseContext != null) {
	
						if (responseContext.getResponses() != null) {
							CDWResponse response = responseContext.getResponses().get(0);
	
							if (response != null) {
								providerList.addAll(response.getCdwProviders());
								if (saveToLocalFiles) {
									saveVistaResponseToFile(responseContext, modifiedOnOrAfterDate.toString(),
											CDWUtils.PROVIDERS_DIR, pageNumber);
								} else {
									cdwNvaProviderService.processCDWNVAProviders(providerList);
									LOG.info("Total number of Providers records -->" + providerList.size());
	
								}
							}
						}
					}
					pageNumber++;
				}
			}
		} catch (RestClientException | PersistenceException | SQLException e) {
			pieErrorService.capturePPMSUpdateError(e, App.CDW, pageNumber);
			throw new RestClientException(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDate
	 * @return
	 * @throws RestClientException
	 */
	public void getCDWProviderServicesFromPPMS(String requestURL, Date modifiedOnOrAfterDate, Integer providerServicesStartPage) throws RestClientException {
		int pageNumber = 1;
		int totalPages;
		try {
			if (providerServicesStartPage != null && providerServicesStartPage > 0) {
				pageNumber = providerServicesStartPage;
			}
			List<CDWProviderService> providerServiceList = new ArrayList<CDWProviderService>();
	
			LOG.info("Calling PPMS to get CDW Provider Services data for modifiedOnOrAfterDate, " + modifiedOnOrAfterDate);
	
			totalPages = getCDWResponseTotalPages(requestURL, modifiedOnOrAfterDate, PAGE_PROVIDER_SERVICES, pageNumber);
	
			if (totalPages > 0) {
				LOG.info("Total pages returned for CDW Provider Services data from PPMS, " + totalPages);
				while (pageNumber <= totalPages) {
					providerServiceList.clear();
					LOG.info("Get CDW Provider Services data for page number, " + pageNumber);
					CDWResponseContext responseContext = getCDWResponseByPageNumber(requestURL, modifiedOnOrAfterDate,
							PAGE_PROVIDER_SERVICES, pageNumber, EXPAND_PROVIDER_SERVICES);
					if (responseContext != null) {
	
						if (responseContext.getResponses() != null) {
							CDWResponse response = responseContext.getResponses().get(0);
	
							if (response != null) {
								providerServiceList.addAll(response.getProviderServices());
								if (saveToLocalFiles) {
									saveVistaResponseToFile(responseContext, modifiedOnOrAfterDate.toString(),
											CDWUtils.PROVIDER_SERVICE_DIR, pageNumber);
								} else {
									cdwProviderServicesService.processProviderServices(providerServiceList);
									LOG.info("Total number of CDW providerServiceModels records -->"
											+ providerServiceList.size());
								}
							}
						}
					}
					pageNumber++;
				}
			}
		} catch (RestClientException | PersistenceException | SQLException e) {
			pieErrorService.capturePPMSUpdateError(e, App.CDW, pageNumber);
			throw new RestClientException(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDate
	 * @return
	 * @throws RestClientException
	 */
	public void getCDWAgreementsFromPPMS(String requestURL, Date modifiedOnOrAfterDate) throws RestClientException {
		int pageNumber = 1;
		int totalPages;
		List<CDWAgreement> agreementList = new ArrayList<CDWAgreement>();
		
		try {
			LOG.info("Calling PPMS to get CDW Agreements data for modifiedOnOrAfterDate, " + modifiedOnOrAfterDate);
	
			totalPages = getCDWResponseTotalPages(requestURL, modifiedOnOrAfterDate, PAGE_AGREEMENTS, pageNumber);
	
			if (totalPages > 0) {
				LOG.info("Total pages returned for CDW Agreements data from PPMS, " + totalPages);
				while (pageNumber <= totalPages) {
					agreementList.clear();
					LOG.info("Get CDW Agreements data for page number, " + pageNumber);
					CDWResponseContext responseContext = getCDWResponseByPageNumber(requestURL, modifiedOnOrAfterDate,
							PAGE_AGREEMENTS, pageNumber, EXPAND_AGREEMENTS);
					if (responseContext != null) {
	
						if (responseContext.getResponses() != null) {
							CDWResponse response = responseContext.getResponses().get(0);
	
							if (response != null) {
								agreementList.addAll(response.getAgreements());
								if (saveToLocalFiles) {
									saveVistaResponseToFile(responseContext, modifiedOnOrAfterDate.toString(),
											CDWUtils.AGREEMENTS_DIR, pageNumber);
								} else {
									cdwAgreementService.processAgreements(agreementList);
									LOG.info("Total number of Agreement records -->" + agreementList.size());
								}
							}
						}
					}
					pageNumber++;
				}
			}
		} catch (RestClientException | PersistenceException | SQLException e) {
			pieErrorService.capturePPMSUpdateError(e, App.CDW, pageNumber);
			throw new RestClientException(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDate
	 * @return
	 * @throws RestClientException
	 */
	public void getCDWCareSitesFromPPMS(String requestURL, Date modifiedOnOrAfterDate, Integer careSiteStartPage) throws RestClientException {
		int pageNumber = 1;
		int totalPages;
		List<CDWCareSite> careSiteList = new ArrayList<CDWCareSite>();
		if (careSiteStartPage != null && careSiteStartPage > 0) {
			pageNumber = careSiteStartPage;
		}
		try {
			LOG.info("Calling PPMS to get CDW CareSite data for modifiedOnOrAfterDate, " + modifiedOnOrAfterDate);
	
			totalPages = getCDWResponseTotalPages(requestURL, modifiedOnOrAfterDate, PAGE_CARE_SITES, pageNumber);
	
			if (totalPages > 0) {
				LOG.info("Total pages returned for CDW Care Sites data from PPMS, " + totalPages);
				while (pageNumber <= totalPages) {
					careSiteList.clear();
					LOG.info("Get CDW Care Sites data for page number, " + pageNumber);
					CDWResponseContext responseContext = getCDWResponseByPageNumber(requestURL, modifiedOnOrAfterDate,
							PAGE_CARE_SITES, pageNumber, EXPAND_CARE_SITES);
					if (responseContext != null) {
	
						if (responseContext.getResponses() != null) {
							CDWResponse response = responseContext.getResponses().get(0);
	
							if (response != null) {
								careSiteList.addAll(response.getCareSites());
								if (saveToLocalFiles) {
									saveVistaResponseToFile(responseContext, modifiedOnOrAfterDate.toString(),
											CDWUtils.CARE_SITES_DIR, pageNumber);
								} else {
									cdwCareSiteService.processCareSites(careSiteList);
									LOG.info("Total number of Care Sites records -->" + careSiteList.size());
								}
							}
						}
					}
					pageNumber++;
				}
			}
		} catch (RestClientException | PersistenceException | SQLException e) {
			pieErrorService.capturePPMSUpdateError(e, App.CDW, pageNumber);
			throw new RestClientException(e.getMessage(), e);
		}

	}

	/**
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDate
	 * @param pageNumberName
	 * @param pageNumber
	 * @return
	 */
	public int getCDWResponseTotalPages(String requestURL, Date modifiedOnOrAfterDate, String pageNumberName,
			int pageNumber) {
		int totalPages = 0;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestURL)
				.queryParam(MODIFIED_ON_AFTER_DATE, modifiedOnOrAfterDate.toString())
				.queryParam(pageNumberName, pageNumber);
		CDWResponseContext responseContext = retryTemplate.execute((context) -> {
			LOG.info("Get CDW response total pages retry account: " + context.getRetryCount());
			return restTemplate.getForObject(builder.toUriString(), CDWResponseContext.class);
		});

		if (responseContext != null) {
			List<CDWResponse> responses = responseContext.getResponses();
			CDWResponse response = responses.get(0);
			totalPages = response.getTotalPages();
		}
		return totalPages;
	}

	/**
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDate
	 * @param pageNumberName
	 * @param pageNumber
	 * @param expand
	 * @return
	 */
	public CDWResponseContext getCDWResponseByPageNumber(String requestURL, Date modifiedOnOrAfterDate,
			String pageNumberName, int pageNumber, String expand) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestURL)
				.queryParam(MODIFIED_ON_AFTER_DATE, modifiedOnOrAfterDate.toString())
				.queryParam(pageNumberName, pageNumber).queryParam(EXPAND, expand);

		CDWResponseContext responseContext = retryTemplate.execute((context) -> {
			LOG.info("Get CDW page " + pageNumber + " retry account: " + context.getRetryCount());
			return restTemplate.getForObject(builder.toUriString(), CDWResponseContext.class);
		});
		return responseContext;
	}

	private void saveVistaResponseToFile(CDWResponseContext responseContext, String dateString, String folderName,
			int pageNumber) {
		// save response to a local files
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String dirPath = cdwBulkFileLocation + File.separator + dateString + File.separator + folderName;
			File newDirectory = new File(dirPath);
			boolean mkdirsRet = newDirectory.mkdirs();
			if(!mkdirsRet) {
				throw new IOException();
			}
			objectMapper.writeValue(new File(dirPath + File.separator + dateString + "_Page_" + pageNumber + ".json"),
					responseContext);
		} catch (IOException e) {
			LOG.error("Convert PPMS VistA non VA provider response to file failed: ");
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
			pieErrorService.capturePPMSUpdateError(e, App.CDW, pageNumber);
		}
	}

}
