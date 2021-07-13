package gov.va.pie.nvappmscdw.utils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

import gov.va.pie.common.entities.CareSite;
import gov.va.pie.common.entities.NonVAProvider;
import gov.va.pie.common.entities.PPMSNonVAInboundResponse;
import gov.va.pie.common.entities.ProviderServiceCareSite;
import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.common.utils.CommonUtils;
import gov.va.pie.nvappmscdw.model.DeaModel;
import gov.va.pie.nvappmscdw.model.ProviderMedicalEducationModel;
import gov.va.pie.nvappmscdw.model.ProviderOtherIdentifierModel;
import gov.va.pie.nvappmscdw.model.ProviderServiceModel;

public class NvaUtils {

	static final Logger LOG = LogManager.getLogger(NvaUtils.class);
	public static final String PROVIDERS_DIR = "provider";
	public static final String PROVIDER_SERVICE_DIR = "providerservice";
	public static final String PROVIDER_IDENTIFIER_DIR = "provideridentifier";
	public static final String PROVIDERS_MEDICAL_EDUCATION_DIR = "medicaleducation";
	public static final String PROVIDERS_DEA_DIR = "dea";
	public static final int EXPECTED_BUFFER_DATA = 5120;
	public static final String VALUES = "VALUES (";

	public static final String INITIALIZATION_FAILURE = "Initialization Failure: Initialization of PPMSNonVAInboundResponse failed. Can't Call PPMS to get Data";

	public static Map<Long, Long> getNpiKeyMap(EntityManager entityManager, List<String> nvaProvidersList) {
		Map<Long, Long> npiMap = new HashMap<>();
		if (CollectionUtils.isEmpty(nvaProvidersList)) {
			return npiMap;
		}
		List<List<String>> partitionedList = Lists.partition(nvaProvidersList, 1999);
		for (List<String> nvaSubList : partitionedList) {
			StringBuilder inClauseTmp = new StringBuilder();
			for (String npi : nvaSubList) {
				if (!StringUtils.isEmpty(String.valueOf(npi))) {
					inClauseTmp.append(" " + npi + " , ");
				}
			}

			if (!StringUtils.isEmpty(inClauseTmp)) {
				String inClause = inClauseTmp.substring(0, inClauseTmp.lastIndexOf(","));
				// @formatter:off
			    String query = "Select cast(NonVAProvider_Id as varchar(10)) NonVAProvider_Id, cast(ProviderNpi as varchar(10)) ProviderNpi "
						     + " from App." + CommonConstants.DB_ENV+ "NonVAProvider_V  "
						     + " Where ProviderNpi in ( " + inClause + " ) ;";
				// @formatter:on
			    query = stripSpecialCharsForSql(query);
				@SuppressWarnings("unchecked")
				List<Object[]> nonVAProvdersList = entityManager.createNativeQuery(query).getResultList();
				for (Object[] nonVAProvder : nonVAProvdersList) {
					npiMap.put(Long.valueOf(nonVAProvder[1].toString()), Long.valueOf(nonVAProvder[0].toString()));
				}
			}
		}
		return npiMap;
	}

	public static CareSite createCareSiteFromProviderServiceModel(ProviderServiceModel providerServiceModel) {

		if (providerServiceModel == null || StringUtils.isEmpty(providerServiceModel.getCareSiteGuid()))
			return null;
		Timestamp addedAt = new Timestamp((new Date()).getTime());
		CareSite careSiteEntity = new CareSite();
		careSiteEntity.setCareSiteGuid(providerServiceModel.getCareSiteGuid());
		careSiteEntity.setCareSiteName(providerServiceModel.getCareSiteName());
		careSiteEntity.setCareSiteType(providerServiceModel.getCareSiteType());
		careSiteEntity.setLatitude(providerServiceModel.getLat());
		careSiteEntity.setLongitude(providerServiceModel.getLon());
		careSiteEntity.setAddressComposite(providerServiceModel.getAddressComposite());
		careSiteEntity.setAddressStreet1(providerServiceModel.getAddressStreet1());
		careSiteEntity.setAddressStreet2(providerServiceModel.getAddressStreet2());
		careSiteEntity.setCity(providerServiceModel.getAddressCity());
		careSiteEntity.setState(providerServiceModel.getAddressState());
		careSiteEntity.setZipCode(providerServiceModel.getAddressZip());
		careSiteEntity.setMainSitePhone(providerServiceModel.getOfficePhone());
		careSiteEntity.setCreatedBy(CommonConstants.PIE);
		careSiteEntity.setCreatedDate(addedAt);
		careSiteEntity.setModifiedBy(CommonConstants.PIE);
		careSiteEntity.setModifiedDate(addedAt);
		return careSiteEntity;
	}

	public static boolean isPPMSNonVAInboundResponseFailureRecord(
			PPMSNonVAInboundResponse vistaPPMSNonVAInboundResponse) {
		if (vistaPPMSNonVAInboundResponse == null)
			return true;
		else
			return vistaPPMSNonVAInboundResponse.getIsProviderFail()
					|| vistaPPMSNonVAInboundResponse.getIsProviderServiceFail()
					|| vistaPPMSNonVAInboundResponse.getIsProviderDEAFail()
					|| vistaPPMSNonVAInboundResponse.getIsProviderOtherIdentifierFail()
					|| vistaPPMSNonVAInboundResponse.getIsProviderMedicalEducationFail();
	}

	public static final String SPACE_COMMA_SPACE_SINGLEQUOTE = " , '";
	public static final String SPACE_COMMA_SPACE = " , ";
	public static final String SINGLEQUOTE = "'";
	public static final String CURRENT_DATE = " getDate()";
	public static final String SINGLEQUOTE_COMMA_SPACE = "', ";

	public static final String NONVAPROVIDER_INSERT = "INSERT INTO App." + CommonConstants.DB_ENV
			+ "NonVAProvider_V (ProviderNpi,ProviderType,ProviderFirstName,ProviderMiddleName,"
			+ "ProviderLastName,MainPhone,Fax,Gender,ProviderStatus, ProviderStatusReason, IsProcessed, PPMSModifiedOn_Date, "
			+ "InactiveFlag, InactiveDate, Created_By, Created_Date, Modified_By, Modified_Date) VALUES ";

	public static String createInsertStatementForNonVAProvider(NonVAProvider nonVAProvider) {
		if (nonVAProvider == null || nonVAProvider.getProviderNpi() < 1) {
			return null;
		}
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(nonVAProvider.getProviderNpi());
		if (StringUtils.isEmpty(nonVAProvider.getProviderType())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getProviderType())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getProviderFirstName())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getProviderFirstName())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getProviderMiddleName())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getProviderMiddleName())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getProviderLastName())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getProviderLastName())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getMainPhone())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getMainPhone()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getFax())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getFax()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getGender())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getGender()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getProviderStatus())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getProviderStatus())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(nonVAProvider.getProviderStatusReason())) {
			insertString.append(SPACE_COMMA_SPACE).append("null").append(", 0 ");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getProviderStatusReason())
					.append(SINGLEQUOTE).append(", 0 ");
		}
		if (nonVAProvider.getPpmsModifiedOnDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(nonVAProvider.getPpmsModifiedOnDate())
					.append(SINGLEQUOTE);
		}
		if (nonVAProvider.isInactiveFlag()) {
			insertString.append(SPACE_COMMA_SPACE).append(1).append(SPACE_COMMA_SPACE).append(CURRENT_DATE);// InactiveDate
		} else {
			insertString.append(SPACE_COMMA_SPACE).append(0).append(SPACE_COMMA_SPACE).append("null");// InactiveDate
		}
		insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(CommonConstants.PIE).append(SINGLEQUOTE)
				.append(SPACE_COMMA_SPACE).append(CURRENT_DATE).append(SPACE_COMMA_SPACE_SINGLEQUOTE)
				.append(CommonConstants.PIE).append(SINGLEQUOTE).append(SPACE_COMMA_SPACE).append(CURRENT_DATE)
				.append("),");
		return insertString.toString();
	}

	public static final String CARESITE_INSERT = "INSERT INTO APP." + CommonConstants.DB_ENV
			+ "CareSite_V (CareSiteGuid,CareSiteType,CareSiteName,"
			+ "AddressComposite,AddressStreet1,AddressStreet2,City,State,ZipCode,Latitude,Longitude,MainSitePhone,AddressValidationConfidenceScore,Created_By,Created_Date,Modified_By,Modified_Date) VALUES ";

	public static final String PROVIDERSERVICECARESITE_INSERT = "INSERT INTO APP." + CommonConstants.DB_ENV
			+ "ProviderServiceCareSite_V (NonVAProvider_Id_FK,CareSite_Id_FK,"
			+ "SpecialityCode,IsPrimaryTaxonomy,ProviderServiceStatus,ProviderServiceStatusReason,"
			+ "PPMSModifiedOn_Date,IsProcessed,InactiveFlag,InactiveDate,Created_By,Created_Date,Modified_By,Modified_Date) VALUES ";

	public static String createInsertStatementForProviderServiceCareSite(ProviderServiceCareSite providerService,
			Long nonVAProviderId, Long careSiteId) {
		if (providerService == null || (providerService.getNonVaprovider() == null && nonVAProviderId == null)
				|| (providerService.getCareSite() == null && careSiteId == null))
			return null;
		StringBuffer insertString = new StringBuffer("(");

		if (nonVAProviderId < 1)
			nonVAProviderId = providerService.getNonVaprovider().getNonVAProviderId();

		if (careSiteId < 1)
			careSiteId = providerService.getCareSite().getCareSiteId();

		insertString.append(nonVAProviderId).append(SPACE_COMMA_SPACE).append(careSiteId);
		if (StringUtils.isEmpty(providerService.getSpecialityCode())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(providerService.getSpecialityCode())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(providerService.getIsPrimaryTaxonomy())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(providerService.getIsPrimaryTaxonomy())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(providerService.getProviderServiceStatus())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(providerService.getProviderServiceStatus())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(providerService.getProviderServiceStatusReason())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(providerService.getProviderServiceStatusReason())
					.append(SINGLEQUOTE);
		}
		if (providerService.getPpmsModifiedOnDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null, 0");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(providerService.getPpmsModifiedOnDate())
					.append(SINGLEQUOTE).append(", 0");
		}
		if (providerService.getInactiveFlag()) {
			insertString.append(SPACE_COMMA_SPACE).append(1).append(SPACE_COMMA_SPACE).append(CURRENT_DATE);// InactiveDate
		} else {
			insertString.append(SPACE_COMMA_SPACE).append(0).append(SPACE_COMMA_SPACE).append("null");// InactiveDate
		}
		insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(CommonConstants.PIE).append(SINGLEQUOTE)
				.append(SPACE_COMMA_SPACE).append(CURRENT_DATE).append(SPACE_COMMA_SPACE_SINGLEQUOTE)
				.append(CommonConstants.PIE).append(SINGLEQUOTE).append(SPACE_COMMA_SPACE).append(CURRENT_DATE)
				.append("),");
		return insertString.toString();
	}

	public static String createInsertStatementForProviderOtherIdentifier(
			ProviderOtherIdentifierModel otherId) {
		if (otherId == null || otherId.getProviderIdentifier() == null)
			return null;
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(otherId.getPrimaryProviderNPI()).append(SPACE_COMMA_SPACE_SINGLEQUOTE)
				.append(otherId.getProviderIdentifier()).append(SINGLEQUOTE);
		if (otherId.getProviderIdentifierType() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null, 0,");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(otherId.getProviderIdentifierType())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(otherId.getProviderIdentifierStatus())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(otherId.getProviderIdentifierStatus())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(otherId.getProviderIdentifierStatusReason())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(otherId.getProviderIdentifierStatusReason())
					.append(SINGLEQUOTE);
		}
		if (otherId.getPpmsModifiedOnDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null,");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(otherId.getPpmsModifiedOnDate())
					.append(SINGLEQUOTE);
		}
		
		insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(CommonConstants.PIE).append(SINGLEQUOTE)
				.append(SPACE_COMMA_SPACE).append(CURRENT_DATE).append(SPACE_COMMA_SPACE_SINGLEQUOTE)
				.append(CommonConstants.PIE).append(SINGLEQUOTE).append(SPACE_COMMA_SPACE).append(CURRENT_DATE)
				.append("),");
		return insertString.toString();
	}

	public static String createInsertStatementForProviderMedicalEducation(ProviderMedicalEducationModel medEdu) {
		if (medEdu == null || medEdu.getEducationName() == null)
			return null;
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(medEdu.getProviderNPI()).append(SPACE_COMMA_SPACE_SINGLEQUOTE)
				.append(medEdu.getEducationName()).append(SINGLEQUOTE);
		if (medEdu.getGraduationDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(medEdu.getGraduationDate()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(medEdu.getMedicalEducationStatus())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(medEdu.getMedicalEducationStatus())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(medEdu.getMedicalEducationStatusReason())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(medEdu.getMedicalEducationStatusReason())
					.append(SINGLEQUOTE);
		}
		if (medEdu.getPpmsModifiedOnDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(medEdu.getPpmsModifiedOnDate())
					.append(SINGLEQUOTE);
		}
		
		insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(CommonConstants.PIE).append(SINGLEQUOTE)
				.append(SPACE_COMMA_SPACE).append(CURRENT_DATE).append(SPACE_COMMA_SPACE_SINGLEQUOTE)
				.append(CommonConstants.PIE).append(SINGLEQUOTE).append(SPACE_COMMA_SPACE).append(CURRENT_DATE)
				.append("),");
		return insertString.toString();
	}

	public static String createInsertStatementForProviderDea(DeaModel dea) {
		if (dea == null || StringUtils.isEmpty(dea.getDeaNumber()))
			return null;
		StringBuffer insertString = new StringBuffer("(");
		insertString.append(dea.getProviderNPI())
			.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(stripSpecialCharsForSql(dea.getDeaNumber()))
			.append(SINGLEQUOTE);
		if (StringUtils.isEmpty(dea.getVerifier())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(stripSpecialCharsForSql(dea.getVerifier())).append(SINGLEQUOTE);
		}
		if (dea.getVerificationDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getVerificationDate()).append(SINGLEQUOTE);
		}
		if (dea.getExpirationDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getExpirationDate()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getAssociatedLocationName())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(stripSpecialCharsForSql(dea.getAssociatedLocationName()))
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getHasScheduleII())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getHasScheduleII()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getHasScheduleIINonNarcotic())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getHasScheduleIINonNarcotic())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getHasScheduleIII())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getHasScheduleIII()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getHasScheduleIIINonNarcotic())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getHasScheduleIIINonNarcotic())
					.append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getHasScheduleIV())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getHasScheduleIV()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getHasScheduleV())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getHasScheduleV()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getDeaStatus())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getDeaStatus()).append(SINGLEQUOTE);
		}
		if (StringUtils.isEmpty(dea.getDeaStatusReason())) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getDeaStatusReason()).append(SINGLEQUOTE);
		}
		if (dea.getPpmsModifiedOnDate() == null) {
			insertString.append(SPACE_COMMA_SPACE).append("null");
		} else {
			insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(dea.getPpmsModifiedOnDate()).append(SINGLEQUOTE);
		}
		
		insertString.append(SPACE_COMMA_SPACE_SINGLEQUOTE).append(CommonConstants.PIE).append(SINGLEQUOTE)
				.append(SPACE_COMMA_SPACE).append(CURRENT_DATE).append(SPACE_COMMA_SPACE_SINGLEQUOTE)
				.append(CommonConstants.PIE).append(SINGLEQUOTE).append(SPACE_COMMA_SPACE).append(CURRENT_DATE)
				.append("),");
		return insertString.toString();
	}

	public static String updateNonVAProvidersQuery(List<Long> providerIdList, int isProcessedStatus) {
		if (CollectionUtils.isEmpty(providerIdList) || isProcessedStatus > 1) {
			return null;
		}
		String inclause = createInClause(providerIdList);
		return "Update App." + CommonConstants.DB_ENV + "NonVAProvider_V set isProcessed = " + isProcessedStatus
				+ ", Modified_by = 'PIE', Modified_Date = getdate() " + " where NONVAPROVIDER_ID in (" + inclause + ")";
	}
	
	public static String updateOutBoundHasUpdatesQuery(List<Long> providerIdList) {
		if (CollectionUtils.isEmpty(providerIdList) ) {
			return null;
		}
		String inclause = createInClause(providerIdList);
		return "Update App." + CommonConstants.DB_ENV + "VistA_OutResponse_V set hasUpdates = 1 , Modified_by = 'PIE', Modified_Date = getdate() where NONVAPROVIDER_ID_FK in (" + inclause + ")";
	}

	public static String createInClause(List<Long> list) {
		if (CollectionUtils.isEmpty(list))
			return "";
		StringBuffer strBuffer = new StringBuffer();
		for (Long id : list) {
			strBuffer.append(id.intValue() + " , ");
		}
		return strBuffer.substring(0, strBuffer.toString().lastIndexOf(","));
	}

	public static void runProcedureInTransaction(EntityManager em, String statementToExecute, DataSource ds) {

		// LOG.info("executed with the statement -- >" + statementToExecute);
		CommonUtils.runProcedure(em, statementToExecute, ds);

	}


	public static Map<Long, Long> generateNonVAProviderNPIToIdMap(EntityManager entityManager) {
		Map<Long, Long> nonVAProviderNPIToIdMap = new HashMap<Long, Long>();

		// get NonVAProvider_Id for ProviderNpi
		@SuppressWarnings("unchecked")
		List<Object[]> nonVAProvdersList = entityManager.createNativeQuery(
				"Select cast(NonVAProvider_Id as varchar(10)) NonVAProvider_Id, cast(ProviderNpi as varchar(10)) ProviderNpi from App."
						+ CommonConstants.DB_ENV + "NonVAProvider_V ")
				.getResultList();
		for (Object[] nonVAProvder : nonVAProvdersList) {
			nonVAProviderNPIToIdMap.put(Long.valueOf(nonVAProvder[1].toString()),
					Long.valueOf(nonVAProvder[0].toString()));
		}
		return nonVAProviderNPIToIdMap;
	}

	/*public static void saveUpdatesStatements(EntityManager entityManager, List<String> updateStatements,
			int batchSize) {
		List<List<String>> UpdateStringLists = Lists.partition(updateStatements, batchSize);
		for (List<String> UpdateStringSubList : UpdateStringLists) {
			StringBuilder updateQueryBatchBuilder = new StringBuilder("");
			for (String updateString : UpdateStringSubList) {
				if (!StringUtils.isEmpty(updateString))
					updateQueryBatchBuilder.append(updateString);
			}
			NvaUtils.runProcedureInTransaction(entityManager, updateQueryBatchBuilder.toString() );

		}
	}*/

	public static String stripSpecialCharsForSql(String str) {

		if (!StringUtils.isEmpty(str)) {
			if (str.contains("'")) {
				str = str.replace("'", "''");
			}
			if (str.contains("%")) {
				str = str.replace("%", "");
			}
			if (str.contains("\\")) {
				str = str.replace("\\", "");
			}

		}
		return str;

	}
	
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) { 
		Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
 	} 
	
	public static String sqlNullChecker(String str) {
		if (StringUtils.isEmpty(str)) {
			return null;
		}
		return "'" + str + "'";
	}
	
}
