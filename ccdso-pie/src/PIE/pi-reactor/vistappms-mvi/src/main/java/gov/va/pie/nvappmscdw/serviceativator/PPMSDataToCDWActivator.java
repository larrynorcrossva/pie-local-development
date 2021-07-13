package gov.va.pie.nvappmscdw.serviceativator;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.va.pie.common.entities.App;
import gov.va.pie.common.entities.PPMSNonVAInboundResponse;
import gov.va.pie.common.repositories.PPMSNonVAInboundResponseRepository;
import gov.va.pie.common.service.PieErrorService;
import gov.va.pie.common.service.PieLogService;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvappmscdw.exception.VistaPharmacyResponseErrorHandler;
import gov.va.pie.nvappmscdw.model.DeaModel;
import gov.va.pie.nvappmscdw.model.NvaProvider;
import gov.va.pie.nvappmscdw.model.ProviderMedicalEducationModel;
import gov.va.pie.nvappmscdw.model.ProviderOtherIdentifierModel;
import gov.va.pie.nvappmscdw.model.ProviderServiceModel;
import gov.va.pie.nvappmscdw.model.VistaPharmacyResponse;
import gov.va.pie.nvappmscdw.model.VistaPharmacyResponseContext;
import gov.va.pie.nvappmscdw.service.GetDataFromPPMS;
import gov.va.pie.nvappmscdw.service.NvaProviderToCDWService;
import gov.va.pie.nvappmscdw.service.ProviderDEASchedulePrivilegesModelToCDWService;
import gov.va.pie.nvappmscdw.service.ProviderMedicalEducationModelToCDWService;
import gov.va.pie.nvappmscdw.service.ProviderOtherIdentifiersModelToCDWService;
import gov.va.pie.nvappmscdw.service.ProviderServiceModelToCDWService;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

@Component("ppmsDataToCDWActivator")
public class PPMSDataToCDWActivator {
	static final Logger LOG = LogManager.getLogger(PPMSDataToCDWActivator.class);

	private static final String PAGE_PARAMETER_PROVIDERS = "providersPage";
	private static final String PAGE_PARAMETER_PROVIDER_SERVICES = "providerServicesPage";
	private static final String PAGE_PARAMETER_PROVIDER_IDENTIFIERS = "providerIdentifiersPage";
	private static final String PAGE_PARAMETER_DEAS = "DEAsPage";
	private static final String PAGE_PARAMETER_MEDICAL_EDUCATIONS = "medicalEducationsPage";
	private static final String EXPAND_PARAMETER_PROVIDERS = "providers";
	private static final String EXPAND_PARAMETER_PROVIDER_SERVICES = "providerservices";
	private static final String EXPAND_PARAMETER_PROVIDER_IDENTIFIERS = "provideridentifiers";
	private static final String EXPAND_PARAMETER_DEAS = "deas";
	private static final String EXPAND_PARAMETER_MEDICAL_EDUCATIONS = "medicaleducations";
	private static final String EXPAND_PARAMETER = "$expand";
	private static final String MODIFIED_ON_AFTER_DATE_PARAMETER = "modifiedOnOrAfterDate";

	private RestTemplate restTemplate;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	@Value("${ppms.provider.endpoint}")
	private String ppmsProviderEndpoint;

	@Value("${ppms.providerservice.endpoint}")
	private String ppmsProviderServiceEndpoint;

	@Value("${ppms.provideridentifier.endpoint}")
	private String ppmsProviderIdentifierEndpoint;

	@Value("${ppms.dea.endpoint}")
	private String ppmsDeaEndpoint;

	@Value("${ppms.medicaleducation.endpoint}")
	private String ppmsMedicalEducationEndpoint;

	@Value("${vista.nva.save.local}")
	private boolean saveToLocalFiles;

	@Value("${vista.bulk.file.location}")
	private String vistaBulkFileLocation;

	@Value("${vista.manual.directory}")
	private String vistaManualDirectory;
	
	@Value("${provider.service.start.page}")
	private Integer providerServiceStartPage;
	
	@Autowired
	private DataSource ds;

	@Autowired
	private NvaProviderToCDWService nvaProviderToCDWService;

	@Autowired
	private ProviderServiceModelToCDWService providerServiceModelToCDWService;

	@Autowired
	private ProviderDEASchedulePrivilegesModelToCDWService deaModelToCDWService;

	@Autowired
	private ProviderMedicalEducationModelToCDWService medEduModelToCDWService;

	@Autowired
	private ProviderOtherIdentifiersModelToCDWService identifierModelToCDWService;

	@Autowired
	private PPMSNonVAInboundResponseRepository ppmsNonVAInboundResponseRepo;

	@Autowired
	private EntityManager entityManager;


	@Autowired
	private EntityManagerFactory emf;

	@Autowired
	private RetryTemplate retryTemplate;

	@Autowired
	private GetDataFromPPMS<NvaProvider> getNvaProviderDataFromPPMS;

	@Autowired
	private GetDataFromPPMS<ProviderServiceModel> getProviderServiceDataFromPPMS;

	@Autowired
	private GetDataFromPPMS<ProviderOtherIdentifierModel> getProviderOtherIdentifierDataFromPPMS;

	@Autowired
	private GetDataFromPPMS<DeaModel> getProviderDeaDataFromPPMS;

	@Autowired
	private GetDataFromPPMS<ProviderMedicalEducationModel> getProviderMedicalEducationDataFromPPMS;
	
	@Autowired
	private PieErrorService pieErrorService;
	
	@Autowired
	private PieLogService pieLogService;

	private String careSiteSationsProcedure = "CalculateCareSitesVastRadius";

	private final String VistATransactionType = "VISTA";
	private final String VistAManualResponseMesssageText = "All extraction files are processed";
	private final String VistADailyResponseMessageText = "All service pulled data";
	private final String VistAManualCreatedBy = "ManualLoad";

	/**
	 * 
	 * @param responseMessage - {@link org.springframework.messaging.Message} object
	 *                        with {@link gov.va.pie.nvappmscdw.model.NvaProvider}
	 *                        as payload. Message and payload cannot be null.
	 * @throws IOException 
	 */
	@ServiceActivator
	@Async

	public void processPPMSData(Message<String> processMessage) throws IOException {
		MessageHeaders headers = processMessage.getHeaders();
		String urlString = (String) headers.get("http_requestUrl");


		if (urlString.contains("mode=manual")) {
			try {
				MultiValueMap<String, String> parameters = UriComponentsBuilder.fromUriString(urlString).build()
						.getQueryParams();
				List<String> dirParam = parameters.get(vistaManualDirectory);
				if (dirParam != null) {
					String dirLocation = dirParam.get(0);
					if (dirLocation != null && !dirLocation.isEmpty()) {
						LOG.info("Bulk file location: " + vistaBulkFileLocation + File.separator + dirLocation);
						manualDataLoadDataFromFiles(dirLocation);
					}
				} else {
					LOG.error("Missing directory parameter in the request!");
					throw new IOException("Missing directory parameter in the request!");
				}
			} catch (IOException e) {
				pieErrorService.captureError(e, App.VISTA);
				throw new IOException(e.getMessage(), e);
			}
		} else {
			pullPPMSDataUpdates();
		}
		if (!saveToLocalFiles) {
			populateCareSiteStations();
		}
	}

    public void populateCareSiteStations() {
        LOG.info("Calcuation care site stations");

        if(ds!=null) 
        {
               LOG.info("Before calling populateCareSiteStations CommonUtils Active: " + ds.getNumActive()+" "+"Idle: " + ds.getNumIdle());
        }
        
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.
        tx.begin();
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("App." + CommonConstants.DB_ENV + careSiteSationsProcedure);
        storedProcedureQuery.executeUpdate();
        tx.commit();
        em.close();
        if(ds!=null) 
        {
               LOG.info("After calling populateCareSiteStations CommonUtils Active: " + ds.getNumActive()+" "+"Idle: " + ds.getNumIdle());
        }

        LOG.info("Done processing " + "App." + CommonConstants.DB_ENV + careSiteSationsProcedure);

 }


	public void moveFromStatingToTables(String procedure) {
        LOG.info("Moving data from Staging for " + procedure);
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
 }


	public void manualDataLoadDataFromFiles(String lastDateOfDataLoad) throws IOException, IllegalArgumentException {
		LOG.info("Processing data from files");
		File dataDirectory = new File(vistaBulkFileLocation + File.separator + lastDateOfDataLoad);
		if (!dataDirectory.exists()) {
			LOG.info(dataDirectory.getAbsolutePath() + " root directory doesn't exist!");
			throw new IOException(dataDirectory.getAbsolutePath() + " root directory doesn't exist!");
		}

		Date lastDayOfManualData = null;
		lastDayOfManualData = Date.valueOf(lastDateOfDataLoad);
		java.util.Date startDate = new java.util.Date();

		// Provider
		getNvaProviderDataFromPPMS.getDataFromFile(
				vistaBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + NvaUtils.PROVIDERS_DIR,
				"Delete from APP." + CommonConstants.DB_ENV + "NonVAProvider_stg_V ", 
				(v)->{
					return v.getNvaProviders();
				}, 
				nvaProviderToCDWService::popuplateStaging, 
				"ProcessProviderVistaData", 
				"VistA Providers");

		// Provider services
		getProviderServiceDataFromPPMS.getDataFromFile(
				vistaBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + NvaUtils.PROVIDER_SERVICE_DIR,
				"Delete from APP." + CommonConstants.DB_ENV + "ProviderService_stg_V ", 
				(v)->{
					return v.getCareSites();
				}, 
				providerServiceModelToCDWService::popuplateStaging, 
				"ProcessProviderServicesVistaData",
				"VistA Provider Services");

		// Provider other identifier
		getProviderOtherIdentifierDataFromPPMS.getDataFromFile(
				vistaBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + NvaUtils.PROVIDER_IDENTIFIER_DIR,
				"Delete from APP." + CommonConstants.DB_ENV + "ProviderOtherIdentifier_stg_V ", 
				(v)->{
					return v.getProviderIdentifiers();
				}, 
				identifierModelToCDWService::popuplateStaging, 
				"ProcessOtherIdentifierVistaData",
				"VistA Provider Other Identifiers");

		// Provider Dea
		getProviderDeaDataFromPPMS.getDataFromFile(
				vistaBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + NvaUtils.PROVIDERS_DEA_DIR,
				"Delete from APP." + CommonConstants.DB_ENV + "ProviderDEASchedulePrivilege_stg_V ", 
				(v)->{
					return v.getDeas();
				}, 
				deaModelToCDWService::popuplateStaging, 
				"ProcessDEASchedulingPrivilegesVistaData",
				"VistA Provider DEA");

		// Provider medical education
		getProviderMedicalEducationDataFromPPMS.getDataFromFile(
				vistaBulkFileLocation + File.separator + lastDateOfDataLoad + File.separator + NvaUtils.PROVIDERS_MEDICAL_EDUCATION_DIR,
				"Delete from APP." + CommonConstants.DB_ENV + "ProviderMedicalEducation_stg_V ", 
				(v)->{
					return v.getMedicalEducations();
				}, 
				medEduModelToCDWService::popuplateStaging, 
				"ProcessMedicalEducationVistaData",
				"VistA Provider Medical Education");

		java.util.Date endDate = new java.util.Date();
		LOG.info("Total time for bulk file processing: " + (endDate.getTime() - startDate.getTime()) / 1000 / 60
				+ " mins");

		// Insert a success record to "PPMSNonVAInboundResponse"
		PPMSNonVAInboundResponse vistaPPMSNonVAInboundResponse = new PPMSNonVAInboundResponse();

		vistaPPMSNonVAInboundResponse.setTransactionNumber(UUID.randomUUID().toString());
		vistaPPMSNonVAInboundResponse.setTransactionCount(0);
		vistaPPMSNonVAInboundResponse.setTransactionType(VistATransactionType);
		vistaPPMSNonVAInboundResponse.setProviderLastSuccessDate(lastDayOfManualData);
		vistaPPMSNonVAInboundResponse.setIsProviderFail(Boolean.FALSE);
		vistaPPMSNonVAInboundResponse.setProviderServiceLastSuccessDate(lastDayOfManualData);
		vistaPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.FALSE);
		vistaPPMSNonVAInboundResponse.setProviderDEALastSuccessDate(lastDayOfManualData);
		vistaPPMSNonVAInboundResponse.setIsProviderDEAFail(Boolean.FALSE);
		vistaPPMSNonVAInboundResponse.setProviderOtherIdentifierLastSuccessDate(lastDayOfManualData);
		vistaPPMSNonVAInboundResponse.setIsProviderOtherIdentifierFail(Boolean.FALSE);
		vistaPPMSNonVAInboundResponse.setProviderMedicalEducationLastSuccessDate(lastDayOfManualData);
		vistaPPMSNonVAInboundResponse.setIsProviderMedicalEducationFail(Boolean.FALSE);
		vistaPPMSNonVAInboundResponse.setCreatedDate((new Timestamp((new java.util.Date()).getTime())));
		vistaPPMSNonVAInboundResponse.setModifiedDate(null);
		vistaPPMSNonVAInboundResponse.setModifiedBy(null);
		vistaPPMSNonVAInboundResponse.setPPMSNonVAInboundResponseId(null);
		vistaPPMSNonVAInboundResponse.setResponseMessageText(VistAManualResponseMesssageText);
		vistaPPMSNonVAInboundResponse.setCreatedBy(VistAManualCreatedBy);
		ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
		LOG.info("Manual VistA file processing is done for the end day: " + lastDateOfDataLoad);
	}

	/**
	 * Process PPMS Data Updates
	 */
	public void pullPPMSDataUpdates() {

		
		LOG.info("Processing daily update from PPMS DWS");
		Date providerModifiedOnOrAfterDateParameter = new Date(new java.util.Date().getTime());

		PPMSNonVAInboundResponse vistaPPMSNonVAInboundResponse = ppmsNonVAInboundResponseRepo
				.findlatestResonse(CommonConstants.PPMSNONVAINBOUNDRESPONSE_VISTA);
		if (vistaPPMSNonVAInboundResponse == null
				|| (vistaPPMSNonVAInboundResponse.getProviderLastSuccessDate() == null)) {
			LOG.info(NvaUtils.INITIALIZATION_FAILURE);
			return;
		} else {
			providerModifiedOnOrAfterDateParameter = vistaPPMSNonVAInboundResponse.getProviderLastSuccessDate();
		}

		final Date modifiedOnOrAfterDate = providerModifiedOnOrAfterDateParameter;
		// create Rest Template with custom error handler
		restTemplate = restTemplateBuilder.errorHandler(new VistaPharmacyResponseErrorHandler()).build();

		
		// providers
		if (getNvaProviderDataFromPPMS.getDataFromWebService(
				providerModifiedOnOrAfterDateParameter, 
				(v)->{
					return v.getNvaProviders();
				},
				(v)->{
					return getVistaPharmacyResponseTotalPages(
							ppmsProviderEndpoint, 
							modifiedOnOrAfterDate,
							PAGE_PARAMETER_PROVIDERS, 
							1
							);
				}, 
				(pageNumber) ->{
					return getVistaPharmacyResponseByPageNumber(
							ppmsProviderEndpoint,
							modifiedOnOrAfterDate, 
							PAGE_PARAMETER_PROVIDERS, 
							pageNumber,
							EXPAND_PARAMETER_PROVIDERS);
				},
				(VistaPharmacyResponseContext v, Integer pageNumber) ->{
					saveVistaResponseToFile(
							v, 
							modifiedOnOrAfterDate.toString(),
							NvaUtils.PROVIDERS_DIR,
							pageNumber);
				},
				nvaProviderToCDWService::popuplateStaging,
				"ProcessProviderVistaData", 
				"VistA Providers",
				0)) {
			vistaPPMSNonVAInboundResponse.setIsProviderFail(Boolean.FALSE);
			//vistaPPMSNonVAInboundResponse.setProviderLastSuccessDate(new Date(new java.util.Date().getTime()));
		}
		else {
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			vistaPPMSNonVAInboundResponse.setTransactionCount(vistaPPMSNonVAInboundResponse.getTransactionCount() + 1);
			vistaPPMSNonVAInboundResponse.setIsProviderFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderDEAFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderOtherIdentifierFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderMedicalEducationFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PPMSNONVAINBOUNDRESPONSE_VISTA);
			vistaPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
			return;
		}
		
		//provider service
		if (getProviderServiceDataFromPPMS.getDataFromWebService(
				providerModifiedOnOrAfterDateParameter, 
				(v)->{
					return v.getCareSites();
				},
				(v)->{
					return getVistaPharmacyResponseTotalPages(
							ppmsProviderServiceEndpoint, 
							modifiedOnOrAfterDate,
							PAGE_PARAMETER_PROVIDER_SERVICES, 
							1
							);
				}, 
				(pageNumber) ->{
					return getVistaPharmacyResponseByPageNumber(
							ppmsProviderServiceEndpoint,
							modifiedOnOrAfterDate, 
							PAGE_PARAMETER_PROVIDER_SERVICES, 
							pageNumber,
							EXPAND_PARAMETER_PROVIDER_SERVICES);
				},
				(VistaPharmacyResponseContext v, Integer pageNumber) ->{
					saveVistaResponseToFile(
							v, 
							modifiedOnOrAfterDate.toString(),
							NvaUtils.PROVIDER_SERVICE_DIR,
							pageNumber);
				},
				providerServiceModelToCDWService::popuplateStaging,
				"ProcessProviderServicesVistaData", 
				"VistA Provider Services",
				providerServiceStartPage)) {
			vistaPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setProviderServiceLastSuccessDate(new Date(new java.util.Date().getTime()));
		}
		else {
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			vistaPPMSNonVAInboundResponse.setTransactionCount(vistaPPMSNonVAInboundResponse.getTransactionCount() + 1);
			vistaPPMSNonVAInboundResponse.setIsProviderServiceFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderDEAFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderOtherIdentifierFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderMedicalEducationFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PPMSNONVAINBOUNDRESPONSE_VISTA);
			vistaPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
			return;
		}
		
		//provider other identifier
		if (getProviderOtherIdentifierDataFromPPMS.getDataFromWebService(
				providerModifiedOnOrAfterDateParameter, 
				(v)->{
					return v.getProviderIdentifiers();
				},
				(v)->{
					return getVistaPharmacyResponseTotalPages(
							ppmsProviderIdentifierEndpoint, 
							modifiedOnOrAfterDate,
							PAGE_PARAMETER_PROVIDER_IDENTIFIERS, 
							1
							);
				}, 
				(pageNumber) ->{
					return getVistaPharmacyResponseByPageNumber(
							ppmsProviderIdentifierEndpoint,
							modifiedOnOrAfterDate, 
							PAGE_PARAMETER_PROVIDER_IDENTIFIERS, 
							pageNumber,
							EXPAND_PARAMETER_PROVIDER_IDENTIFIERS);
				},
				(VistaPharmacyResponseContext v, Integer pageNumber) ->{
					saveVistaResponseToFile(
							v, 
							modifiedOnOrAfterDate.toString(),
							NvaUtils.PROVIDER_IDENTIFIER_DIR,
							pageNumber);
				},
				identifierModelToCDWService::popuplateStaging,
				"ProcessOtherIdentifierVistaData", 
				"VistA Provider Other Identifier",
				0)) {
			vistaPPMSNonVAInboundResponse.setIsProviderOtherIdentifierFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setProviderOtherIdentifierLastSuccessDate(new Date(new java.util.Date().getTime()));
		}
		else {
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			vistaPPMSNonVAInboundResponse.setTransactionCount(vistaPPMSNonVAInboundResponse.getTransactionCount() + 1);
			vistaPPMSNonVAInboundResponse.setIsProviderOtherIdentifierFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderDEAFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderMedicalEducationFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PPMSNONVAINBOUNDRESPONSE_VISTA);
			vistaPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
			return;
		}
		
		//provider DEA
		if (getProviderDeaDataFromPPMS.getDataFromWebService(
				providerModifiedOnOrAfterDateParameter, 
				(v)->{
					return v.getDeas();
				},
				(v)->{
					return getVistaPharmacyResponseTotalPages(
							ppmsDeaEndpoint, 
							modifiedOnOrAfterDate,
							PAGE_PARAMETER_DEAS, 
							1
							);
				}, 
				(pageNumber) ->{
					return getVistaPharmacyResponseByPageNumber(
							ppmsDeaEndpoint,
							modifiedOnOrAfterDate, 
							PAGE_PARAMETER_DEAS, 
							pageNumber,
							EXPAND_PARAMETER_DEAS);
				},
				(VistaPharmacyResponseContext v, Integer pageNumber) ->{
					saveVistaResponseToFile(
							v, 
							modifiedOnOrAfterDate.toString(),
							NvaUtils.PROVIDERS_DEA_DIR,
							pageNumber);
				},
				deaModelToCDWService::popuplateStaging,
				"ProcessDEASchedulingPrivilegesVistaData", 
				"VistA Provider DEA",
				0)) {
			vistaPPMSNonVAInboundResponse.setIsProviderDEAFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setProviderDEALastSuccessDate(new Date(new java.util.Date().getTime()));
		}
		else {
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			vistaPPMSNonVAInboundResponse.setTransactionCount(vistaPPMSNonVAInboundResponse.getTransactionCount() + 1);
			vistaPPMSNonVAInboundResponse.setIsProviderDEAFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setIsProviderMedicalEducationFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PPMSNONVAINBOUNDRESPONSE_VISTA);
			vistaPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
			return;
		}
		  
		//provider medical education services
		if (getProviderMedicalEducationDataFromPPMS.getDataFromWebService(
				providerModifiedOnOrAfterDateParameter, 
				(v)->{
					return v.getMedicalEducations();
				},
				(v)->{
					return getVistaPharmacyResponseTotalPages(
							ppmsMedicalEducationEndpoint, 
							modifiedOnOrAfterDate,
							PAGE_PARAMETER_MEDICAL_EDUCATIONS, 
							1
							);
				}, 
				(pageNumber) ->{
					return getVistaPharmacyResponseByPageNumber(
							ppmsMedicalEducationEndpoint,
							modifiedOnOrAfterDate, 
							PAGE_PARAMETER_MEDICAL_EDUCATIONS, 
							pageNumber,
							EXPAND_PARAMETER_MEDICAL_EDUCATIONS);
				},
				(VistaPharmacyResponseContext v, Integer pageNumber) ->{
					saveVistaResponseToFile(
							v, 
							modifiedOnOrAfterDate.toString(),
							NvaUtils.PROVIDERS_MEDICAL_EDUCATION_DIR,
							pageNumber);
				},
				medEduModelToCDWService::popuplateStaging,
				"ProcessMedicalEducationVistaData", 
				"VistA Provider Medical Education",
				0)) {
			vistaPPMSNonVAInboundResponse.setIsProviderMedicalEducationFail(Boolean.FALSE);
			vistaPPMSNonVAInboundResponse.setProviderMedicalEducationLastSuccessDate(new Date(new java.util.Date().getTime()));
		}
		else {
			Timestamp stampedAt = new Timestamp((new java.util.Date()).getTime());
			vistaPPMSNonVAInboundResponse.setTransactionCount(vistaPPMSNonVAInboundResponse.getTransactionCount() + 1);
			vistaPPMSNonVAInboundResponse.setIsProviderMedicalEducationFail(Boolean.TRUE);
			vistaPPMSNonVAInboundResponse.setModifiedBy(CommonConstants.PPMSNONVAINBOUNDRESPONSE_VISTA);
			vistaPPMSNonVAInboundResponse.setModifiedDate(stampedAt);
			ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
			return;
		}
		if (!saveToLocalFiles) {
			entityManager.detach(vistaPPMSNonVAInboundResponse);
			vistaPPMSNonVAInboundResponse.setTransactionNumber(UUID.randomUUID().toString());
			vistaPPMSNonVAInboundResponse.setTransactionCount(0);
			vistaPPMSNonVAInboundResponse.setCreatedBy(CommonConstants.PIE);
			vistaPPMSNonVAInboundResponse.setResponseMessageText(VistADailyResponseMessageText);
			vistaPPMSNonVAInboundResponse.setCreatedDate(new Timestamp((new java.util.Date()).getTime()));
			vistaPPMSNonVAInboundResponse.setModifiedDate(null);
			vistaPPMSNonVAInboundResponse.setModifiedBy(null);
			vistaPPMSNonVAInboundResponse.setPPMSNonVAInboundResponseId(null);
			vistaPPMSNonVAInboundResponse.setProviderLastSuccessDate(new Date(new java.util.Date().getTime()));
			ppmsNonVAInboundResponseRepo.save(vistaPPMSNonVAInboundResponse);
		}
		LOG.info("Successfull Processed all daily updates");
		
	}

	/**
	 * Get total number of pages returned in the VistA Pharmacy Response
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDateParameter
	 * @param pageNumberParameterName
	 * @param pageNumber
	 * 
	 * @return
	 */
	public int getVistaPharmacyResponseTotalPages(String requestURL, Date modifiedOnOrAfterDateParameter,
			String pageNumberParameterName, int pageNumber) throws IllegalArgumentException {

		int totalPages = 0;
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestURL)
				.queryParam(MODIFIED_ON_AFTER_DATE_PARAMETER, modifiedOnOrAfterDateParameter.toString())
				.queryParam(pageNumberParameterName, pageNumber);
		VistaPharmacyResponseContext responseContext = retryTemplate.execute( (context) -> {
			LOG.info("Get VistA response total pages retry account: " + context.getRetryCount());
			return restTemplate.getForObject(builder.toUriString(), VistaPharmacyResponseContext.class);
		});
		if (responseContext != null) {
			// get response object
			if (responseContext.getResponses() != null) {
				VistaPharmacyResponse response = responseContext.getResponses().get(0);

				// get total pages
				totalPages = response.getTotalPages();
			}
		}
		return totalPages;
	}

	/**
	 * Get VistA Pharmacy Response Context by calling PPMS endpoint
	 * 
	 * @param requestURL
	 * @param modifiedOnOrAfterDateParameter
	 * @param pageNumberParameterName
	 * @param pageNumber
	 * @param expandParameter
	 * 
	 * @return
	 */
	public VistaPharmacyResponseContext getVistaPharmacyResponseByPageNumber(String requestURL,
			Date modifiedOnOrAfterDateParameter, String pageNumberParameterName, int pageNumber,
			String expandParameter) {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(requestURL)
				.queryParam(MODIFIED_ON_AFTER_DATE_PARAMETER, modifiedOnOrAfterDateParameter.toString())
				.queryParam(pageNumberParameterName, pageNumber).queryParam(EXPAND_PARAMETER, expandParameter);
		VistaPharmacyResponseContext responseContext = retryTemplate.execute( (context) -> {
			LOG.info("Get VistA page " + pageNumber + " retry account: " + context.getRetryCount());
			return restTemplate.getForObject(builder.toUriString(), VistaPharmacyResponseContext.class);
		});
		return responseContext;
	}

	private void saveVistaResponseToFile(VistaPharmacyResponseContext responseContext, String dateString, String folderName, int pageNumber) {
		//save response to a local files
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String dirPath = vistaBulkFileLocation + File.separator 
					+ dateString + File.separator + folderName; 	
			File newDirectory = new File(dirPath);
			boolean mkdirsRet = newDirectory.mkdirs();
			if(!mkdirsRet) {
				throw new IOException();
			}
			objectMapper.writeValue(new File(dirPath + File.separator + dateString + "_Page_" + pageNumber + ".json"), responseContext);
		} catch (IOException e) {
			LOG.error("Convert PPMS VistA non VA provider response to file failed: ");
			pieLogService.recordDetails(e, CommonConstants.LOG_ERROR);
		}
	}
}
