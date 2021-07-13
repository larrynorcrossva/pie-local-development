package gov.va.pie.nvacdwvista.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import gov.va.pie.nvacdwvista.model.DeaModel;
import gov.va.pie.nvacdwvista.model.MviModel;
import gov.va.pie.nvacdwvista.model.SpecialityModel;


public class MviModelBuilder {

	private Map<String, Set<DeaModel>> deaHashMap;
	private Map<String, Set<SpecialityModel>> specialityHashMap;
	private Set<String> proivderCareSiteStationSet;
	private static String YES = "Y";
	private static String NO = "N";
	private static int NON_VA_PROVIDER_ID_INDEX = 0;
	private static int NPI_INDEX = 1;
	private static int FIST_NAME_INDEX = 2;
	private static int LAST_NAME_INDEX = 3;
	private static int MIDDLE_NAME_INDEX = 4;
	private static int STATION_INDEX = 5;
	private static int DEA_INDEX = 6;
	private static int MAIN_PHONE_INDEX = 7;
	private static int SPECIALITY_INDEX = 8;
	private static int STREET_INDEX = 9;
	private static int CITY_INDEX = 10;
	private static int SATE_INDEX = 11;
	private static int ZIP_INDEX = 12;
	private static int HAS_SCHEDULE_II_INDEX = 13;
	private static int HAS_SCHEDULE_III_INDEX = 14;
	private static int HAS_SCHEDULE_IV_INDEX = 15;
	private static int HAS_SCHEDULE_II_NON_NORC_INDEX = 16;
	private static int HAS_SCHEDULE_III_NON_NORC_INDEX = 17;
	private static int HAS_SCHEDULE_V_INDEX = 18;
	private static int EXPIRATION_DATE_INDEX = 19;
	private static int OTHER_ID_INDEX = 20;
	private static int CARE_SITE_ID_INDEX = 21;
	private static int CARE_SITE_PHONE_INDEX = 22;
	private static int GENDER_INDEX = 23;
	private static int SPECIALTIY_INACTIVE_INDEX = 24;
	private static int SPECIALTIY_INACTIVE_DATE_INDEX = 25;
	private static int CARE_SITE_STATIONS_ID_INDEX = 26;
	private static int PROVIDER_SERVICES_CARE_SITE_ID = 27;
	private static int VISTA_OUT_RESPONSE_ID = 28;
	private static int IS_RETRIEVED_INDEX = 29;
	private static int DEA_INACTIVE_INDEX=30;
	private static int DEA_INACTIVE_DATE_INDEX=31;
	private static int DEA_STATUS_REASON_INDEX=32;
	private static int DETOX_NUMBER_INDEX = 33;
	private static int PROVIDER_STATUS_Reason_INDEX = 34;

	private static String GENDER_MALE = "Male";
	private static String GENDER_FEMALE = "FeMale";
	private static String GENDER_OTHER = "Othter";
	private static String GENDER_NOT_SPECIFIED = "NOT_SPECIFIED";
	private static String GENDERNOTSPECIFIED = "NOTSPECIFIED";

	public List<MviModel> getNonVaProviders(List<Object[]> resultList) {

		List<MviModel> mviModelsList = new ArrayList<>();
		proivderCareSiteStationSet = new HashSet<String>();
		populateMaps(resultList);

		for (Object[] record : resultList) {
			String key = record[NON_VA_PROVIDER_ID_INDEX].toString() + "-" + record[STATION_INDEX].toString();
			if (!proivderCareSiteStationSet.contains(key)) {
				proivderCareSiteStationSet.add(key);
				MviModel mviModel = new MviModel();
				String nvaProviderKey = null;
				if (record[NON_VA_PROVIDER_ID_INDEX] != null) {
					nvaProviderKey = record[NON_VA_PROVIDER_ID_INDEX].toString();
					mviModel.setNonVaproviderId(nvaProviderKey);
				}
				if (record[NPI_INDEX] != null) {
					mviModel.setNpi(record[NPI_INDEX].toString());
				}

				if (record[FIST_NAME_INDEX] != null) {
					mviModel.setFirstName(record[FIST_NAME_INDEX].toString());
				}

				if (record[LAST_NAME_INDEX] != null) {
					mviModel.setLastName(record[LAST_NAME_INDEX].toString());
				}

				if (record[4] != null) {
					mviModel.setMiddleName(record[MIDDLE_NAME_INDEX].toString());
				}

				if (record[STATION_INDEX] != null) {
					mviModel.setStationNumber(record[STATION_INDEX].toString());

				}
				if (record[STREET_INDEX] != null) {
					mviModel.setStreeAddress1(record[STREET_INDEX].toString());
				}

				if (record[CITY_INDEX] != null) {
					mviModel.setCity(record[CITY_INDEX].toString());
				}
				if (record[SATE_INDEX] != null) {
					mviModel.setState(record[SATE_INDEX].toString());
				}
				if (record[ZIP_INDEX] != null) {
					mviModel.setZip(record[ZIP_INDEX].toString());
				}

				if (record[GENDER_INDEX] != null) {
					if (GENDER_MALE.equalsIgnoreCase(record[GENDER_INDEX].toString()))
						mviModel.setGender("M");
					if (GENDER_FEMALE.equalsIgnoreCase(record[GENDER_INDEX].toString()))
						mviModel.setGender("F");
					if (GENDER_OTHER.equalsIgnoreCase(record[GENDER_INDEX].toString()))
						mviModel.setGender("O");

					if (GENDER_NOT_SPECIFIED.equalsIgnoreCase(record[GENDER_INDEX].toString())) // NOT_SPECIFIED
						mviModel.setGender("I");
					if (GENDERNOTSPECIFIED.equalsIgnoreCase(record[GENDER_INDEX].toString())) // NOTSPECIFIED
						mviModel.setGender("I");

				}

				if (record[CARE_SITE_PHONE_INDEX] != null)
					mviModel.setOfficePhone(record[22].toString());

				mviModel.setDeaModelList(deaHashMap.get(nvaProviderKey));
				String specialitesKey = nvaProviderKey + "::" + record[STATION_INDEX].toString();
				mviModel.setSpecialitiesList(specialityHashMap.get(specialitesKey));
				mviModel.setAuthToWriteMedOrders(deaHashMap.get(nvaProviderKey) == null ? "0" : "1");
				if (record[CARE_SITE_STATIONS_ID_INDEX] != null) {
					mviModel.setCareSiteStationId(record[CARE_SITE_STATIONS_ID_INDEX].toString());
				}
				if (record[PROVIDER_SERVICES_CARE_SITE_ID] != null) {
					mviModel.setProviderSerivicesCareSiteId(record[PROVIDER_SERVICES_CARE_SITE_ID].toString());
				}
				if (record[CARE_SITE_ID_INDEX] != null) {
					mviModel.setCareSiteId(record[CARE_SITE_ID_INDEX].toString());
				}

				if (record[VISTA_OUT_RESPONSE_ID] != null)
					mviModel.setVistaOutResponseId(record[VISTA_OUT_RESPONSE_ID].toString());		
					
				if (record[PROVIDER_STATUS_Reason_INDEX] != null)
					mviModel.setProviderStatusReason(record[PROVIDER_STATUS_Reason_INDEX].toString());
				
				mviModelsList.add(mviModel);

			}

		}

		return mviModelsList;

	}

	private void populateMaps(List<Object[]> resultsList) {
		deaHashMap = new HashMap<>();
		specialityHashMap = new HashMap<>();
		String nvaProviderKey = null;
		for (Object[] record : resultsList) {
			if (record[NON_VA_PROVIDER_ID_INDEX] != null) {
				nvaProviderKey = record[NON_VA_PROVIDER_ID_INDEX].toString();
			}

			if (record[DEA_INDEX] != null) {
				DeaModel deaModel = new DeaModel();
				deaModel.setDeaNumber(record[DEA_INDEX].toString());
				if (record[EXPIRATION_DATE_INDEX] != null) {
					deaModel.setExpirationDate(record[EXPIRATION_DATE_INDEX].toString());
				}

				StringBuilder schedule = new StringBuilder();

				if (record[HAS_SCHEDULE_II_INDEX] != null && record[HAS_SCHEDULE_III_INDEX] != null
						&& record[HAS_SCHEDULE_IV_INDEX] != null && record[HAS_SCHEDULE_II_NON_NORC_INDEX] != null
						&& record[HAS_SCHEDULE_III_NON_NORC_INDEX] != null && record[HAS_SCHEDULE_V_INDEX] != null) {
					schedule.append(deaModel.getDeaNumber() + "^");
					schedule.append(YES.equalsIgnoreCase(record[HAS_SCHEDULE_II_INDEX].toString()) ? "1" : "0")
							.append("^");
					schedule.append(YES.equalsIgnoreCase(record[HAS_SCHEDULE_III_INDEX].toString()) ? "1" : "0")
							.append("^");
					schedule.append(YES.equalsIgnoreCase(record[HAS_SCHEDULE_IV_INDEX].toString()) ? "1" : "0")
							.append("^");
					schedule.append(YES.equalsIgnoreCase(record[HAS_SCHEDULE_II_NON_NORC_INDEX].toString()) ? "1" : "0")
							.append("^");
					schedule.append(
							YES.equalsIgnoreCase(record[HAS_SCHEDULE_III_NON_NORC_INDEX].toString()) ? "1" : "0")
							.append("^");
					schedule.append(YES.equalsIgnoreCase(record[HAS_SCHEDULE_V_INDEX].toString()) ? "1" : "0");
					// @formatter:on
					deaModel.setHasSchedule(schedule.toString());
				}
				if(record[DEA_STATUS_REASON_INDEX]!=null) { deaModel.setDeaStatusReason(record[DEA_STATUS_REASON_INDEX].toString()); }
				if(record[DEA_INACTIVE_DATE_INDEX]!=null) { deaModel.setInactiveDate(record[DEA_INACTIVE_DATE_INDEX].toString()); }
				if(record[DEA_INACTIVE_INDEX]!=null) { deaModel.setInactiveFlag((boolean)record[DEA_INACTIVE_INDEX]); }
				if (deaHashMap.get(nvaProviderKey) == null) {
					Set<DeaModel> tmpList = new HashSet<>();
					tmpList.add(deaModel);
					deaHashMap.put(nvaProviderKey, tmpList);
				} else {
					deaHashMap.get(nvaProviderKey).add(deaModel);
				}
				if (record[DEA_INDEX] != null && record[DETOX_NUMBER_INDEX] != null)
				{
					deaModel.setDetoxNumber(record[DEA_INDEX].toString() + "^" + record[DETOX_NUMBER_INDEX].toString());
				}
			}
			if (record[SPECIALITY_INDEX] != null) {
				SpecialityModel specialityModel = new SpecialityModel();
				specialityModel.setSpecialityCode(record[SPECIALITY_INDEX].toString());
				if (record[SPECIALTIY_INACTIVE_INDEX] != null) {
					specialityModel.setSpecialityActive(!Boolean.valueOf(record[SPECIALTIY_INACTIVE_INDEX].toString()));
				}
				if (record[SPECIALTIY_INACTIVE_DATE_INDEX] != null) {
					specialityModel.setSpecialityinActiveDate(record[SPECIALTIY_INACTIVE_DATE_INDEX].toString());
				}
				String key = nvaProviderKey + "::" + record[STATION_INDEX].toString();

				if (specialityHashMap.get(key) == null) {
					Set<SpecialityModel> tmpList = new HashSet<>();
					tmpList.add(specialityModel);
					specialityHashMap.put(key, tmpList);
					
				} else {
					specialityHashMap.get(key).add(specialityModel);
					
				}
			}

		}
	}
}
