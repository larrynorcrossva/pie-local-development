package gov.va.pie.utils;

import javax.xml.datatype.DatatypeConfigurationException;

import org.springframework.util.StringUtils;

import gov.va.pie.cdw.model.VAProvider;
import gov.va.pie.ppms.model.DeaSchedulePrivilege;
import gov.va.pie.ppms.model.DeaSchedulePrivileges;
import gov.va.pie.ppms.model.GenderType;
import gov.va.pie.ppms.model.IdentifierTypeCodeList;
import gov.va.pie.ppms.model.Individual;
import gov.va.pie.ppms.model.Npi;
import gov.va.pie.ppms.model.Npis;
import gov.va.pie.ppms.model.OtherIdentifier;
import gov.va.pie.ppms.model.OtherIdentifiers;
import gov.va.pie.ppms.model.OtherNameType;
import gov.va.pie.ppms.model.Provider;
import gov.va.pie.ppms.model.Provider.Type;
import gov.va.pie.ppms.model.ProviderOtherName;
import gov.va.pie.ppms.model.ProviderOtherNames;
import gov.va.pie.ppms.model.ProviderService;
import gov.va.pie.ppms.model.ProviderServices;
import gov.va.pie.ppms.model.RelationshipType;
import gov.va.pie.ppms.model.Specialties;
import gov.va.pie.ppms.model.Taxonomy;
import gov.va.pie.ppms.model.TransactionTypeList;

/**
 * 
 * @author AbleVets
 * 
 *
 */
public class PieUtils {

	public static final int PPMS_MESSAGAE_LIMIT = 2000;
	public static final int PARTITION_SIZE_WHILE_READING_CDW_PROVIDERS = 50000;
	public static final String CDW_TO_PIE_DATA_EXTRACTOR = "CdwToPIEDataExtractor";
	public static final String GENDER_MALE = "Male";
	public static final String GENDER_FEMALE = "Female";
	public static final String GENDER_NOT_SPECIFIED = "Not Specified";
	public static final String GENDER_OTHER = "Other";
	public static final String X12CODE_NULL = "-1";
	public static final String TRANSACTION_ID = "transactionId";
	public static final String CONVERSATION_NUMBER = "X-ConversationID";
	public static final String ORIGINAL_PAYLOAD = "originalPayload";
	public static final String HTTP_STATUS_OK = "200";
	public static final String HTTP_STATUS_OK_CREATED = "201";
	public static final String SYSTEM = "PIE";
	public static final int MAX_RESULT_HEADER_LENGTH = 500;
	

	// List of nodes in processing results
	public static final String FIRST_NAME = "FirstName";
	public static final String LAST_NAME = "LastName";
	public static final String EMAIL = "Email";
	public static final String PHONE = "Phone";
	public static final String FAX = "Fax";
	public static final String GENDER = "Gender";
	public static final String NPI = "Npi";
	public static final String SPECIALITIES = "Specialties";
	public static final String PROVIDER_SERVICES = "ProviderServices";
	public static final String DEA_NUMBER = "DeaNumber";
	public static final String OTHER_IDENTIFIERS = "OtherIdentifiers";
	public static final String PROVIDER = "Provider";
	public static final String PROVIDER_OTHER_NAMES = "ProviderOtherNames";
	
	
	/**
	 * Method to convert a {@link gov.va.pie.cdw.model.VAProvider}
	 * to {@link gov.va.pie.ppms.model.VAProvider }
	 * 
	 * @param vaProvider
	 *            - {@link gov.va.pie.cdw.model.VAProvider} object
	 * @return {@link gov.va.pie.ppms.model.Provider } object
	 */
	public static Provider convertVAProviderToPPMSProvider(VAProvider vaProvider)
			throws DatatypeConfigurationException {
		
		Provider ppmsProvider = new Provider();
		ppmsProvider.setProviderId(vaProvider.getId() + "");
		ppmsProvider.setTransactionType(TransactionTypeList.UPDATE);

		if (vaProvider.getTerminationDate() != null) {
			ppmsProvider.setTransactionType(TransactionTypeList.DEACTIVATE_PROVIDER);
		}

		Npi npi = new Npi();
		npi.setNumber(vaProvider.getNpi());
		npi.setEntityTypeCode("1-Individual");
		Npis npis = new Npis();
		npis.getItem().add(npi);
		ppmsProvider.setNpis(npis);
		ppmsProvider.setStationNumber(vaProvider.getSta6a());
		
		ProviderServices providerServices = new ProviderServices();
		ProviderService providerService = new ProviderService();
		
		String x12Code = vaProvider.getX12Code();
		if (x12Code != null && !x12Code.equals(PieUtils.X12CODE_NULL)) {
			Specialties specialities = new Specialties();
			Taxonomy taxonomy = new Taxonomy();
			taxonomy.setCodedSpecialty(vaProvider.getX12Code());
			providerService.setCodedSpecialty(vaProvider.getX12Code());
			taxonomy.setIsPrimaryTaxonomy(true);
			specialities.getItem().add(taxonomy);
			ppmsProvider.setSpecialties(specialities);
		}
		providerService.setStationNumber(vaProvider.getSta6a());
		providerService.setRelationship(RelationshipType.VA_PROVIDER);
		providerServices.getItem().add(providerService);
		
		Individual individual = new Individual();
		individual.setFirstName(vaProvider.getFirstName());
		individual.setLastName(vaProvider.getLastName());
		individual.setProviderServices(providerServices);
		String gender = vaProvider.getGender();
		if (!StringUtils.isEmpty(gender)) {
			if (gender.equalsIgnoreCase(PieUtils.GENDER_FEMALE))
				individual.setGender(GenderType.FEMALE);
			else
				if (gender.equalsIgnoreCase(PieUtils.GENDER_MALE))
					individual.setGender(GenderType.MALE);
				else
					if (gender.equalsIgnoreCase(PieUtils.GENDER_OTHER))
						individual.setGender(GenderType.OTHER);
					else
						if (gender.equalsIgnoreCase(PieUtils.GENDER_NOT_SPECIFIED))
							individual.setGender(GenderType.NOT_SPECIFIED);
		}
		else {
			individual.setGender(GenderType.NOT_SPECIFIED);
		}

		String deaNumber = vaProvider.getDea();
		if (!StringUtils.isEmpty(deaNumber)) {
			DeaSchedulePrivileges deaPrivilages = new DeaSchedulePrivileges();
			DeaSchedulePrivilege privilage = new DeaSchedulePrivilege();
			privilage.setDeaNumber(vaProvider.getDea());
			deaPrivilages.getItem().add(privilage);
			individual.setDeaNumbers(deaPrivilages);
		}

		String staffName = vaProvider.getStaffName();
		if (!StringUtils.isEmpty(staffName)) {
			ProviderOtherName name = new ProviderOtherName();
			name.setName(staffName);
			name.setOtherNameType(OtherNameType.PROFESSIONAL_NAME);
			ProviderOtherNames names = new ProviderOtherNames();
			names.getItem().add(name);
			individual.setProviderOtherNames(names);
		}
		Type providerType = new Type();
		providerType.setIndividual(individual);
		ppmsProvider.setType(providerType);

		OtherIdentifier staffSid = new OtherIdentifier();
		OtherIdentifier staffIen = new OtherIdentifier();
		OtherIdentifiers ids = new OtherIdentifiers();

		if (vaProvider.getStaffSid() > 0) {

			staffSid.setName(vaProvider.getStaffSid() + "");
			staffSid.setIdentifierTypeCode(IdentifierTypeCodeList.STAFF_SID);
			ids.getItem().add(staffSid);
		}

		if (!StringUtils.isEmpty(vaProvider.getStaffIEN())) {
			staffIen.setName(vaProvider.getStaffIEN());
			staffIen.setIdentifierTypeCode(IdentifierTypeCodeList.STAFF_IEN);
			ids.getItem().add(staffIen);
		}
		if (ids.getItem().size() > 0)
			ppmsProvider.setOtherIdentifiers(ids);
		return ppmsProvider;
	}
}
