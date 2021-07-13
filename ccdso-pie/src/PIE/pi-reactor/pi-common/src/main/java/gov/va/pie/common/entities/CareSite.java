package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;

/**
 * The persistent class for the CareSite database table.
 * 
 */
@Entity
@Table(name = CommonConstants.DB_ENV + "CareSite_V")
public class CareSite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CareSite_Id")
	private long careSiteId;

	@Column(name = "AddressComposite")
	private String addressComposite;
	
	@Column(name = "AddressStreet1")
	private String addressStreet1;
	
	@Column(name = "AddressStreet2")
	private String addressStreet2;

	@Column(name = "AddressValidationConfidenceScore")
	private int addressValidationConfidenceScore;

	@Column(name = "BingConfidenceLevel")
	private String bingConfidenceLevel;

	@Column(name = "BingSuggestedPostalAddress")
	private String bingSuggestedPostalAddress;

	@Column(name = "CareSiteEmail")
	private String careSiteEmail;

	@Column(name = "CareSiteFax")
	private String careSiteFax;

	@Column(name = "CareSiteGuid")
	private String careSiteGuid;

	@Column(name = "CareSiteName")
	private String careSiteName;

	@Column(name = "CareSiteStatus")
	private String careSiteStatus;
	
	@Column(name = "CareSiteStatusReason")
	private String careSiteStatusReason;
	
	@Column(name = "CareSiteType")
	private String careSiteType;

	@Column(name = "CenterOfExcellence")
	private String centerOfExcellence;

	@Column(name = "City")
	private String city;

	@Column(name = "Country")
	private String country;

	@Column(name = "Created_By")
	private String createdBy;

	@Column(name = "Created_Date")
	private Timestamp createdDate;

	private String DEANumber;

	@Column(name = "DeliveryPointValidation")
	private String deliveryPointValidation;

	@Column(name = "Facility")
	private String facility;

	@Column(name = "FacilityType")
	private String facilityType;

	@Column(name = "IsAddressValidated")
	private String isAddressValidated;

	@Column(name = "IsExternal")
	private String isExternal;
	
	@Column(name = "IsGeocoded")
	private String isGeocoded;
	
	@Column(name = "IsHandicapAccessible")
	private String isHandicapAccessible;

	@Column(name = "IsUseAddressValidationService")
	private String isUseAddressValidationService;
	
	@Column(name = "IsUseGeocodingService")
	private String isUseGeocodingService;

	@Column(name = "IsVACareSite")
	private String isVACareSite;
	
	@Column(name = "Latitude")
	private double latitude;

	@Column(name = "Longitude")
	private double longitude;

	@Column(name = "MainSitePhone")
	private String mainSitePhone;

	@Column(name = "Modified_By")
	private String modifiedBy;

	@Column(name = "Modified_Date")
	private Timestamp modifiedDate;

	@Column(name = "OtherName")
	private String otherName;
	
	@Column(name = "Owner")
	private String owner;
	
	@Column(name = "OwningBusinessUnit")
	private String owningBusinessUnit;
	
	@Column(name = "OwningTeam")
	private String owningTeam;
	
	@Column(name = "OwningUser")
	private String owningUser;
	
	@Column(name = "ParentStationNumber")
	private String parentStationNumber;
	
	@Column(name="PPMSCreated_By")
	private String ppmsCreatedBy;

	@Column(name="PPMSCreatedOn_Date")
	private Timestamp ppmsCreatedOnDate;

	@Column(name = "PPMSInactive_Date")
	private Date ppmsInactiveDate;
	
	@Column(name="PPMSModified_By")
	private String ppmsModifiedBy;

	@Column(name = "PPMSModifiedOn_Date")
	private Date ppmsModifiedOnDate;
	
	@Column(name = "ProviderGroup")
	private String providerGroup;

	@Column(name = "SiteContact")
	private String siteContact;
	
	@Column(name = "State")
	private String state;

	@Column(name = "StationName")
	private String stationName;
	
	@Column(name = "Visn")
	private String visn;

	@Column(name = "ZipCode")
	private String zipCode;

	@OneToMany(mappedBy = "careSite", fetch = FetchType.LAZY)
	private List<CareSiteStation> careSiteStations;

	public CareSite() {
	}

	public long getCareSiteId() {
		return careSiteId;
	}

	public void setCareSiteId(long careSiteId) {
		this.careSiteId = careSiteId;
	}

	public String getAddressComposite() {
		return addressComposite;
	}

	public void setAddressComposite(String addressComposite) {
		this.addressComposite = addressComposite;
	}

	public String getAddressStreet1() {
		return addressStreet1;
	}

	public void setAddressStreet1(String addressStreet1) {
		this.addressStreet1 = addressStreet1;
	}

	public String getAddressStreet2() {
		return addressStreet2;
	}

	public void setAddressStreet2(String addressStreet2) {
		this.addressStreet2 = addressStreet2;
	}

	public int getAddressValidationConfidenceScore() {
		return addressValidationConfidenceScore;
	}

	public void setAddressValidationConfidenceScore(int addressValidationConfidenceScore) {
		this.addressValidationConfidenceScore = addressValidationConfidenceScore;
	}

	public String getBingConfidenceLevel() {
		return bingConfidenceLevel;
	}

	public void setBingConfidenceLevel(String bingConfidenceLevel) {
		this.bingConfidenceLevel = bingConfidenceLevel;
	}

	public String getBingSuggestedPostalAddress() {
		return bingSuggestedPostalAddress;
	}

	public void setBingSuggestedPostalAddress(String bingSuggestedPostalAddress) {
		this.bingSuggestedPostalAddress = bingSuggestedPostalAddress;
	}

	public String getCareSiteEmail() {
		return careSiteEmail;
	}

	public void setCareSiteEmail(String careSiteEmail) {
		this.careSiteEmail = careSiteEmail;
	}

	public String getCareSiteFax() {
		return careSiteFax;
	}

	public void setCareSiteFax(String careSiteFax) {
		this.careSiteFax = careSiteFax;
	}

	public String getCareSiteGuid() {
		return careSiteGuid;
	}

	public void setCareSiteGuid(String careSiteGuid) {
		this.careSiteGuid = careSiteGuid;
	}

	public String getCareSiteName() {
		return careSiteName;
	}

	public void setCareSiteName(String careSiteName) {
		this.careSiteName = careSiteName;
	}

	public String getCareSiteStatus() {
		return careSiteStatus;
	}

	public void setCareSiteStatus(String careSiteStatus) {
		this.careSiteStatus = careSiteStatus;
	}

	public String getCareSiteStatusReason() {
		return careSiteStatusReason;
	}

	public void setCareSiteStatusReason(String careSiteStatusReason) {
		this.careSiteStatusReason = careSiteStatusReason;
	}

	public String getCareSiteType() {
		return careSiteType;
	}

	public void setCareSiteType(String careSiteType) {
		this.careSiteType = careSiteType;
	}

	public String getCenterOfExcellence() {
		return centerOfExcellence;
	}

	public void setCenterOfExcellence(String centerOfExcellence) {
		this.centerOfExcellence = centerOfExcellence;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getDEANumber() {
		return DEANumber;
	}

	public void setDEANumber(String dEANumber) {
		DEANumber = dEANumber;
	}

	public String getDeliveryPointValidation() {
		return deliveryPointValidation;
	}

	public void setDeliveryPointValidation(String deliveryPointValidation) {
		this.deliveryPointValidation = deliveryPointValidation;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(String facilityType) {
		this.facilityType = facilityType;
	}

	public String getIsAddressValidated() {
		return isAddressValidated;
	}

	public void setIsAddressValidated(String isAddressValidated) {
		this.isAddressValidated = isAddressValidated;
	}

	public String getIsExternal() {
		return isExternal;
	}

	public void setIsExternal(String isExternal) {
		this.isExternal = isExternal;
	}

	public String getIsGeocoded() {
		return isGeocoded;
	}

	public void setIsGeocoded(String isGeocoded) {
		this.isGeocoded = isGeocoded;
	}

	public String getIsHandicapAccessible() {
		return isHandicapAccessible;
	}

	public void setIsHandicapAccessible(String isHandicapAccessible) {
		this.isHandicapAccessible = isHandicapAccessible;
	}

	public String getIsVACareSite() {
		return isVACareSite;
	}

	public void setIsVACareSite(String isVACareSite) {
		this.isVACareSite = isVACareSite;
	}

	public String getIsUseAddressValidationService() {
		return isUseAddressValidationService;
	}

	public void setIsUseAddressValidationService(String isUseAddressValidationService) {
		this.isUseAddressValidationService = isUseAddressValidationService;
	}

	public String getIsUseGeocodingService() {
		return isUseGeocodingService;
	}

	public void setIsUseGeocodingService(String isUseGeocodingService) {
		this.isUseGeocodingService = isUseGeocodingService;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getMainSitePhone() {
		return mainSitePhone;
	}

	public void setMainSitePhone(String mainSitePhone) {
		this.mainSitePhone = mainSitePhone;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwningBusinessUnit() {
		return owningBusinessUnit;
	}

	public void setOwningBusinessUnit(String owningBusinessUnit) {
		this.owningBusinessUnit = owningBusinessUnit;
	}

	public String getOwningTeam() {
		return owningTeam;
	}

	public void setOwningTeam(String owningTeam) {
		this.owningTeam = owningTeam;
	}

	public String getOwningUser() {
		return owningUser;
	}

	public void setOwningUser(String owningUser) {
		this.owningUser = owningUser;
	}

	public String getParentStationNumber() {
		return parentStationNumber;
	}

	public void setParentStationNumber(String parentStationNumber) {
		this.parentStationNumber = parentStationNumber;
	}

	public String getPpmsCreatedBy() {
		return ppmsCreatedBy;
	}

	public void setPpmsCreatedBy(String ppmsCreatedBy) {
		this.ppmsCreatedBy = ppmsCreatedBy;
	}

	public Timestamp getPpmsCreatedOnDate() {
		return ppmsCreatedOnDate;
	}

	public void setPpmsCreatedOnDate(Timestamp ppmsCreatedOnDate) {
		this.ppmsCreatedOnDate = ppmsCreatedOnDate;
	}

	public Date getPpmsInactiveDate() {
		return ppmsInactiveDate;
	}

	public void setPpmsInactiveDate(Date ppmsInactiveDate) {
		this.ppmsInactiveDate = ppmsInactiveDate;
	}

	public String getPpmsModifiedBy() {
		return ppmsModifiedBy;
	}

	public void setPpmsModifiedBy(String ppmsModifiedBy) {
		this.ppmsModifiedBy = ppmsModifiedBy;
	}

	public Date getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(Date ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

	public String getProviderGroup() {
		return providerGroup;
	}

	public void setProviderGroup(String providerGroup) {
		this.providerGroup = providerGroup;
	}

	public String getSiteContact() {
		return siteContact;
	}

	public void setSiteContact(String siteContact) {
		this.siteContact = siteContact;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getVisn() {
		return visn;
	}

	public void setVisn(String visn) {
		this.visn = visn;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<CareSiteStation> getCareSiteStations() {
		return this.careSiteStations;
	}

	public void setCareSiteStations(List<CareSiteStation> careSiteStations) {
		this.careSiteStations = careSiteStations;
	}

	public CareSiteStation addCareSiteStation(CareSiteStation careSiteStation) {
		getCareSiteStations().add(careSiteStation);
		careSiteStation.setCareSite(this);
		return careSiteStation;
	}

	public CareSiteStation removePpmsProviderResponseDetail(CareSiteStation careSiteStation) {
		getCareSiteStations().remove(careSiteStation);
		careSiteStation.setCareSite(this);
		return careSiteStation;
	}


	

}