package gov.va.pie.nvacdwvista.model;

import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;

public class MviModel {

	private static final String PROVIDER_STATUS_ACTIVE = "Active";

	private String key;
	private boolean isNew;
	private String NonVaproviderId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String stationNumber;
	private Set<String> taxIdorTin;
	private Set<SpecialityModel> specialitiesList;
	private String streeAddress1;
	private String streeAddress2;
	private String city;
	private String state;
	private String zip;
	private String mainPhone;
	private String officePhone;
	private String fax;
	private String title;
	private String degree;
	private String authToWriteMedOrders;
	private String gender;
	private String providerStatus;
	private String providerStatusReason;
	private String npi;
	private Set<DeaModel> deaModelList;
	private String careSiteStationId;
	private String providerSerivicesCareSiteId;
	private String careSiteId;
	private String vistaOutResponseId;

	public String getNpi() {
		return npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public Set<String> getTaxIdorTin() {
		return taxIdorTin;
	}

	public void setTaxIdorTin(Set<String> taxIdorTin) {
		this.taxIdorTin = taxIdorTin;
	}

	public String getMainPhone() {
		return mainPhone;
	}

	public void setMainPhone(String mainPhone) {
		this.mainPhone = mainPhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProviderStatus() {
		return providerStatus;
	}

	public void setProviderStatus(String providerStatus) {
		this.providerStatus = providerStatus;
	}

	public String getProviderStatusReason() {
		return providerStatusReason;
	}

	public void setProviderStatusReason(String providerStatusReason) {
		this.providerStatusReason = providerStatusReason;
	}

	public String getNonVaproviderId() {
		return NonVaproviderId;
	}

	public void setNonVaproviderId(String nonVaproviderId) {
		NonVaproviderId = nonVaproviderId;
	}

	public Set<DeaModel> getDeaModelList() {
		return deaModelList;
	}

	public void setDeaModelList(Set<DeaModel> deaModelList) {
		this.deaModelList = deaModelList;
	}

	public String getStreeAddress1() {
		return streeAddress1;
	}

	public void setStreeAddress1(String streeAddress1) {
		this.streeAddress1 = streeAddress1;
	}

	public String getStreeAddress2() {
		return streeAddress2;
	}

	public void setStreeAddress2(String streeAddress2) {
		this.streeAddress2 = streeAddress2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAuthToWriteMedOrders() {
		return authToWriteMedOrders;
	}

	public void setAuthToWriteMedOrders(String authToWriteMedOrders) {
		this.authToWriteMedOrders = authToWriteMedOrders;
	}


	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public String getCareSiteStationId() {
		return careSiteStationId;
	}

	public void setCareSiteStationId(String careSiteStationId) {
		this.careSiteStationId = careSiteStationId;
	}

	public String getProviderSerivicesCareSiteId() {
		return providerSerivicesCareSiteId;
	}

	public void setProviderSerivicesCareSiteId(String providerSerivicesCareSiteId) {
		this.providerSerivicesCareSiteId = providerSerivicesCareSiteId;
	}

	public String getCareSiteId() {
		return careSiteId;
	}

	public void setCareSiteId(String careSiteId) {
		this.careSiteId = careSiteId;
	}

	public String getVistaOutResponseId() {
		return vistaOutResponseId;
	}

	public void setVistaOutResponseId(String vistaOutResponseId) {
		this.vistaOutResponseId = vistaOutResponseId;
	}

	public Set<SpecialityModel> getSpecialitiesList() {
		return specialitiesList;
	}

	public void setSpecialitiesList(Set<SpecialityModel> specialitiesList) {
		this.specialitiesList = specialitiesList;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean hasAcitveSpecialites() {
		if (CollectionUtils.isEmpty(specialitiesList))
			return false;
		for (SpecialityModel speciality : specialitiesList) {
			if (speciality.getSpecialityActive())
				return true;
		}
		return false;
	}

	public boolean isActiveProvider() {
		if (providerStatusReason == null)
			return false;

		if (providerStatusReason.equalsIgnoreCase(PROVIDER_STATUS_ACTIVE))
			return true;
		else
			return false;
	}

}
