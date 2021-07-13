package gov.va.pie.nvappms.cdw.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CDWCareSite
 * <p>
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CDWCareSite {
	/**
    *
    * (Required)
    *
    */
   @JsonProperty("CareSiteGuid")
   String careSiteGuid;
   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("AddressComposite")
   String addressComposite;
   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CareSiteEmail")
   String careSiteEmail;
   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CareSiteFax")
   String careSiteFax;
   /**
    *
    * 
    *
    */
   @JsonProperty("CenterOfExcellence")
   String centerOfExcellence;
   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("City")
   String city;
   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Country")
   String country;
   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Facility")
   String facility;
   /**
    *
    * 
    *
    */
   @JsonProperty("FacilityType")
   String facilityType;
   /**
    *
    * 
    *
    */
   @JsonProperty("Latitude")
   Double latitude;
   /**
    *
    * 
    *
    */
   @JsonProperty("Longitude")
   Double longitude;
   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("MainSitePhone")
   String mainSitePhone;
   /**
    *
    *
    *
    */
   @JsonProperty("ModifiedOnDate")
   String modifiedOnDate;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Name")
   String name;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Owner")
   String owner;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ProviderGroup")
   String providerGroup;
   /**
    *
    *
    *
    */
   @JsonProperty("CareSiteType")
   String careSiteType;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("State")
   String state;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("StationName")
   String stationName;
   /**
    *
    *
    *
    */
   @JsonProperty("CareSiteStatus")
   String careSiteStatus;
   /**
    *
    *
    *
    */
   @JsonProperty("CareSiteStatusReason")
   String careSiteStatusReason;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("Street")
   String street;
   /**
    *
    *
    *
    */
   @JsonProperty("Street2")
   String street2;
   /**
    *
    * 
    *
    */
   @JsonProperty("VACareSite")
   Boolean vACareSite;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ZipCode")
   String zipCode;
   /**
    *
    * 
    *
    */
   @JsonProperty("ActiveDate")
   String activeDate;
   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidated")
   Boolean addressValidated;
   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidationConfidenceScore")
   Integer addressValidationConfidenceScore;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("BingConfidenceLevel")
   String bingConfidenceLevel;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("BingSuggestedPostalAddress")
   String bingSuggestedPostalAddress;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("CreatedBy")
   String createdBy;
   /**
    *
    * 
    *
    */
   @JsonProperty("CreatedOn")
   String createdOn;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("DEANumber")
   String dEANumber;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("DeliveryPointValidation")
   String deliveryPointValidation;
   /**
    *
    * 
    *
    */
   @JsonProperty("Geocoded")
   Boolean geocoded;
   /**
    *
    * 
    *
    */
   @JsonProperty("InactiveDate")
   String inactiveDate;
   /**
    *
    * 
    *
    */
   @JsonProperty("IsExternal")
   Boolean isExternal;
   /**
    *
    * 
    *
    */
   @JsonProperty("IsHandicapAccessible")
   Boolean isHandicapAccessible;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   String modifiedBy;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OtherName")
   String otherName;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningBusinessUnit")
   String owningBusinessUnit;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningTeam")
   String owningTeam;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningUser")
   String owningUser;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ParentStationNumber")
   String parentStationNumber;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("SiteContact")
   String siteContact;
   /**
    *
    * 
    *
    */
   @JsonProperty("TimeZoneRuleVersionNumber")
   Integer timeZoneRuleVersionNumber;
   /**
    *
    * 
    *
    */
   @JsonProperty("UseAddressValidationService")
   Boolean useAddressValidationService;
   /**
    *
    * 
    *
    */
   @JsonProperty("UseBingGeocodingService")
   Boolean useBingGeocodingService;
   /**
    *
    * 
    *
    */
   @JsonProperty("UTCConversionTimeZoneCode")
   Integer uTCConversionTimeZoneCode;
   /**
    *
    * 
    *
    */
   @JsonProperty("VersionNumber")
   Integer versionNumber;
   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("VISN")
   String vISN;

   /**
    *
    * (Required)   
    *
    */
   @JsonProperty("CareSiteGuid")
   public String getCareSiteGuid() {
       return careSiteGuid;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CareSiteGuid")
   public void setCareSiteGuid(String careSiteGuid) {
       this.careSiteGuid = careSiteGuid;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("AddressComposite")
   public String getAddressComposite() {
       return addressComposite;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("AddressComposite")
   public void setAddressComposite(String addressComposite) {
       this.addressComposite = addressComposite;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CareSiteEmail")
   public String getCareSiteEmail() {
       return careSiteEmail;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CareSiteEmail")
   public void setCareSiteEmail(String careSiteEmail) {
       this.careSiteEmail = careSiteEmail;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CareSiteFax")
   public String getCareSiteFax() {
       return careSiteFax;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("CareSiteFax")
   public void setCareSiteFax(String careSiteFax) {
       this.careSiteFax = careSiteFax;
   }
   
   /**
    *
    * 
    *
    */
   @JsonProperty("CenterOfExcellence")
   public String getCenterOfExcellence() {
       return centerOfExcellence;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CenterOfExcellence")
   public void setCenterOfExcellence(String centerOfExcellence) {
       this.centerOfExcellence = centerOfExcellence;
   }


   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("City")
   public String getCity() {
       return city;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("City")
   public void setCity(String city) {
       this.city = city;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Country")
   public String getCountry() {
       return country;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Country")
   public void setCountry(String country) {
       this.country = country;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Facility")
   public String getFacility() {
       return facility;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Facility")
   public void setFacility(String facility) {
       this.facility = facility;
   }
   
   /**
    *
    * 
    *
    */
   @JsonProperty("FacilityType")
   public String getFacilityType() {
       return facilityType;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("FacilityType")
   public void setFacilityType(String facilityType) {
       this.facilityType = facilityType;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Latitude")
   public double getLatitude() {
       return latitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Latitude")
   public void setLatitude(double latitude) {
       this.latitude = latitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Longitude")
   public double getLongitude() {
       return longitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Longitude")
   public void setLongitude(double longitude) {
       this.longitude = longitude;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("MainSitePhone")
   public String getMainSitePhone() {
       return mainSitePhone;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("MainSitePhone")
   public void setMainSitePhone(String mainSitePhone) {
       this.mainSitePhone = mainSitePhone;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ModifiedOnDate")
   public String getModifiedOnDate() {
       return modifiedOnDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ModifiedOnDate")
   public void setModifiedOnDate(String modifiedOnDate) {
       this.modifiedOnDate = modifiedOnDate;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Name")
   public String getName() {
       return name;
   }

   /**
    *
    * (Required) 
    *
    */
   @JsonProperty("Name")
   public void setName(String name) {
       this.name = name;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("Owner")
   public String getOwner() {
       return owner;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("Owner")
   public void setOwner(String owner) {
       this.owner = owner;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ProviderGroup")
   public String getProviderGroup() {
       return providerGroup;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ProviderGroup")
   public void setProviderGroup(String providerGroup) {
       this.providerGroup = providerGroup;
   }
   
   /**
    *
    *  
    *
    */
   @JsonProperty("CareSiteType")
   public String getCareSiteType() {
       return careSiteType;
   }

   /**
    *
    *  
    *
    */
   @JsonProperty("CareSiteType")
   public void setCareSiteType(String careSiteType) {
       this.careSiteType = careSiteType;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("State")
   public String getState() {
       return state;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("State")
   public void setState(String state) {
       this.state = state;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("StationName")
   public String getStationName() {
       return stationName;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("StationName")
   public void setStationName(String stationName) {
       this.stationName = stationName;
   }
   
   /**
    *
    *  
    *
    */
   @JsonProperty("CareSiteStatus")
   public String getCareSiteStatus() {
       return careSiteStatus;
   }

   /**
    *
    *  
    *
    */
   @JsonProperty("CareSiteStatus")
   public void setCareSiteStatus(String careSiteStatus) {
       this.careSiteStatus = careSiteStatus;
   }
   /**
    *
    *  
    *
    */
   @JsonProperty("CareSiteStatusReason")
   public String getCareSiteStatusReason() {
       return careSiteStatusReason;
   }

   /**
    *
    *  
    *
    */
   @JsonProperty("CareSiteStatusReason")
   public void setCareSiteStatusReason(String careSiteStatusReason) {
       this.careSiteStatusReason = careSiteStatusReason;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("Street")
   public String getStreet() {
       return street;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("Street")
   public void setStreet(String street) {
       this.street = street;
   }
   
   /**
    *
    *  
    *
    */
   @JsonProperty("Street2")
   public String getStreet2() {
       return street2;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Street2")
   public void setStreet2(String street2) {
       this.street2 = street2;
   }


   /**
    *
    * 
    *
    */
   @JsonProperty("VACareSite")
   public Boolean getVACareSite() {
       return vACareSite;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("VACareSite")
   public void setVACareSite(Boolean vACareSite) {
       this.vACareSite = vACareSite;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ZipCode")
   public String getZipCode() {
       return zipCode;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ZipCode")
   public void setZipCode(String zipCode) {
       this.zipCode = zipCode;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ActiveDate")
   public String getActiveDate() {
       return activeDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ActiveDate")
   public void setActiveDate(String activeDate) {
       this.activeDate = activeDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidated")
   public Boolean getAddressValidated() {
       return addressValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidated")
   public void setAddressValidated(Boolean addressValidated) {
       this.addressValidated = addressValidated;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidationConfidenceScore")
   public Integer getAddressValidationConfidenceScore() {
       return addressValidationConfidenceScore;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("AddressValidationConfidenceScore")
   public void setAddressValidationConfidenceScore(Integer addressValidationConfidenceScore) {
       this.addressValidationConfidenceScore = addressValidationConfidenceScore;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("BingConfidenceLevel")
   public String getBingConfidenceLevel() {
       return bingConfidenceLevel;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("BingConfidenceLevel")
   public void setBingConfidenceLevel(String bingConfidenceLevel) {
       this.bingConfidenceLevel = bingConfidenceLevel;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("BingSuggestedPostalAddress")
   public String getBingSuggestedPostalAddress() {
       return bingSuggestedPostalAddress;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("BingSuggestedPostalAddress")
   public void setBingSuggestedPostalAddress(String bingSuggestedPostalAddress) {
       this.bingSuggestedPostalAddress = bingSuggestedPostalAddress;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("CreatedBy")
   public String getCreatedBy() {
       return createdBy;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("CreatedBy")
   public void setCreatedBy(String createdBy) {
       this.createdBy = createdBy;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CreatedOn")
   public String getCreatedOn() {
       return createdOn;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("CreatedOn")
   public void setCreatedOn(String createdOn) {
       this.createdOn = createdOn;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("DEANumber")
   public String getDEANumber() {
       return dEANumber;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("DEANumber")
   public void setDEANumber(String dEANumber) {
       this.dEANumber = dEANumber;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("DeliveryPointValidation")
   public String getDeliveryPointValidation() {
       return deliveryPointValidation;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("DeliveryPointValidation")
   public void setDeliveryPointValidation(String deliveryPointValidation) {
       this.deliveryPointValidation = deliveryPointValidation;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Geocoded")
   public Boolean getGeocoded() {
       return geocoded;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Geocoded")
   public void setGeocoded(Boolean geocoded) {
       this.geocoded = geocoded;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("InactiveDate")
   public String getInactiveDate() {
       return inactiveDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("InactiveDate")
   public void setInactiveDate(String inactiveDate) {
       this.inactiveDate = inactiveDate;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IsExternal")
   public Boolean getIsExternal() {
       return isExternal;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IsExternal")
   public void setIsExternal(Boolean isExternal) {
       this.isExternal = isExternal;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IsHandicapAccessible")
   public Boolean getIsHandicapAccessible() {
       return isHandicapAccessible;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("IsHandicapAccessible")
   public void setIsHandicapAccessible(Boolean isHandicapAccessible) {
       this.isHandicapAccessible = isHandicapAccessible;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   public String getModifiedBy() {
       return modifiedBy;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   public void setModifiedBy(String modifiedBy) {
       this.modifiedBy = modifiedBy;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OtherName")
   public String getOtherName() {
       return otherName;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OtherName")
   public void setOtherName(String otherName) {
       this.otherName = otherName;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningBusinessUnit")
   public String getOwningBusinessUnit() {
       return owningBusinessUnit;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningBusinessUnit")
   public void setOwningBusinessUnit(String owningBusinessUnit) {
       this.owningBusinessUnit = owningBusinessUnit;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningTeam")
   public String getOwningTeam() {
       return owningTeam;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningTeam")
   public void setOwningTeam(String owningTeam) {
       this.owningTeam = owningTeam;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningUser")
   public String getOwningUser() {
       return owningUser;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("OwningUser")
   public void setOwningUser(String owningUser) {
       this.owningUser = owningUser;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ParentStationNumber")
   public String getParentStationNumber() {
       return parentStationNumber;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("ParentStationNumber")
   public void setParentStationNumber(String parentStationNumber) {
       this.parentStationNumber = parentStationNumber;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("SiteContact")
   public String getSiteContact() {
       return siteContact;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("SiteContact")
   public void setSiteContact(String siteContact) {
       this.siteContact = siteContact;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("TimeZoneRuleVersionNumber")
   public Integer getTimeZoneRuleVersionNumber() {
       return timeZoneRuleVersionNumber;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("TimeZoneRuleVersionNumber")
   public void setTimeZoneRuleVersionNumber(Integer timeZoneRuleVersionNumber) {
       this.timeZoneRuleVersionNumber = timeZoneRuleVersionNumber;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("UseAddressValidationService")
   public Boolean getUseAddressValidationService() {
       return useAddressValidationService;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("UseAddressValidationService")
   public void setUseAddressValidationService(Boolean useAddressValidationService) {
       this.useAddressValidationService = useAddressValidationService;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("UseBingGeocodingService")
   public Boolean getUseBingGeocodingService() {
       return useBingGeocodingService;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("UseBingGeocodingService")
   public void setUseBingGeocodingService(Boolean useBingGeocodingService) {
       this.useBingGeocodingService = useBingGeocodingService;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("UTCConversionTimeZoneCode")
   public Integer getUTCConversionTimeZoneCode() {
       return uTCConversionTimeZoneCode;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("UTCConversionTimeZoneCode")
   public void setUTCConversionTimeZoneCode(Integer uTCConversionTimeZoneCode) {
       this.uTCConversionTimeZoneCode = uTCConversionTimeZoneCode;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("VersionNumber")
   public Integer getVersionNumber() {
       return versionNumber;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("VersionNumber")
   public void setVersionNumber(Integer versionNumber) {
       this.versionNumber = versionNumber;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("VISN")
   public String getVISN() {
       return vISN;
   }

   /**
    *
    *  (Required)
    *
    */
   @JsonProperty("VISN")
   public void setVISN(String vISN) {
       this.vISN = vISN;
   }
   
   @Override
   public String toString() {
   return new ToStringBuilder(this)
   		.append("careSiteGuid", careSiteGuid)
   		.append("addressComposite", addressComposite)
   		.append("careSiteEmail", careSiteEmail)
   		.append("careSiteFax", careSiteFax)
   		.append("centerOfExcellence", centerOfExcellence)
   		.append("city", city)
   		.append("country", country)
   		.append("facility", facility)
   		.append("facilityType", facilityType)
   		.append("latitude", latitude)
   		.append("longitude", longitude)
   		.append("mainSitePhone", mainSitePhone)
   		.append("modifiedOnDate", modifiedOnDate)
   		.append("name", name)
   		.append("owner", owner)
   		.append("providerGroup", providerGroup)
   		.append("careSiteType", careSiteType)
   		.append("state", state)
   		.append("stationName", stationName)
   		.append("careSiteStatus", careSiteStatus)
   		.append("careSiteStatusReason", careSiteStatusReason)
   		.append("street", street)
   		.append("street2", street2)
   		.append("vACareSite", vACareSite)
   		.append("zipCode", zipCode)
   		.append("activeDate", activeDate)
   		.append("addressValidated", addressValidated)
   		.append("addressValidationConfidenceScore", addressValidationConfidenceScore)
   		.append("bingConfidenceLevel", bingConfidenceLevel)
   		.append("bingSuggestedPostalAddress", bingSuggestedPostalAddress)
   		.append("createdBy", createdBy)
   		.append("createdOn", createdOn)
   		.append("dEANumber", dEANumber)
   		.append("deliveryPointValidation", deliveryPointValidation)
   		.append("geocoded", geocoded)
   		.append("inactiveDate", inactiveDate)
   		.append("isExternal", isExternal)
   		.append("isHandicapAccessible", isHandicapAccessible)
   		.append("modifiedBy", modifiedBy)
   		.append("otherName", otherName)
   		.append("owningBusinessUnit", owningBusinessUnit)
   		.append("owningTeam", owningTeam)
   		.append("owningUser", owningUser)
   		.append("parentStationNumber", parentStationNumber)
   		.append("siteContact", siteContact)
   		.append("timeZoneRuleVersionNumber", timeZoneRuleVersionNumber)
   		.append("useAddressValidationService", useAddressValidationService)
   		.append("useBingGeocodingService", useBingGeocodingService)
   		.append("uTCConversionTimeZoneCode", uTCConversionTimeZoneCode)
   		.append("versionNumber", versionNumber)
   		.append("vISN", vISN)
   		.toString();
   }
}
