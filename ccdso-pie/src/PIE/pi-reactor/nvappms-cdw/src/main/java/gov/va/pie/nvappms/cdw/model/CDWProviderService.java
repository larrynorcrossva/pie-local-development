package gov.va.pie.nvappms.cdw.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * CDWProviderService
 * <p>
 *
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CDWProviderService {
	/**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderServiceGuid")
   String providerServiceGuid;
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
   @JsonProperty("CareSiteGuid")
   String careSiteGuid;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("CareSiteName")
   String careSiteName;
   
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
   @JsonProperty("Geocoded")
   Boolean geocoded;
   /**
    *
    * 
    *
    */
   @JsonProperty("HPP")
   String hPP;
   /**
    *
    * 
    *
    */
   @JsonProperty("HSRM")
   Boolean hSRM;
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
   @JsonProperty("OrganizationGroup")
   String organizationGroup;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Owner")
   String owner;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderName")
   String providerName;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderAgreement")
   String providerAgreement;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderNpi")
   long providerNpi;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderIsPrimaryCare")
   Boolean providerIsPrimaryCare;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderNetwork")
   String providerNetwork;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialityName")
   String specialtyName;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialityCode")
   String specialtyCode;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderServiceStatus")
   String providerServiceStatus;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderServiceStatusReason")
   String providerServiceStatusReason;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("VAProviderRelationship")
   String vAProviderRelationship;
   /**
    *
    * (Required)
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
    * (Required)
    *
    */
   @JsonProperty("DescriptionOfService")
   String descriptionOfService;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DupCheck")
   String dupCheck;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Limitation")
   String limitation;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   String modifiedBy;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessUnit")
   String owningBusinessUnit;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessTeam")
   String owningTeam;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningUser")
   String owningUser;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderAcceptingNewPatients")
   Boolean providerAcceptingNewPatients;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderAcceptingVA")
   Boolean providerAcceptingVA;
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderGender")
   String providerGender;
   /**
    *
    * 
    *
    */
   @JsonProperty("QualityRankingTotalScore")
   Integer qualityRankingTotalScore;
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
   @JsonProperty("UTCConversionTimeZoneCode")
   Integer uTCConversionTimeZoneCode;
   /**
    *
    * 
    *
    */
   @JsonProperty("VersionNumber")
   String versionNumber;
   /**
    *
    * (Required)
    *
    */
   @JsonProperty("WorkHours")
   String workHours;

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderServiceGuid")
   public String getProviderServiceGuid() {
       return providerServiceGuid;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderServiceGuid")
   public void setProviderServiceGuid(String providerServiceGuid) {
       this.providerServiceGuid = providerServiceGuid;
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
   @JsonProperty("CareSiteGuid")
   public String getCareSiteGuid() {
       return careSiteGuid;
   }

   /**
    *
    * 
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
   @JsonProperty("CareSiteName")
   public String getCareSiteName() {
       return careSiteName;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("CareSiteName")
   public void setCareSiteName(String careSiteName) {
       this.careSiteName = careSiteName;
   }

   /**
    *
    * (Required)
    *
    */
   
  
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
   @JsonProperty("HPP")
   public String getHPP() {
       return hPP;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("HPP")
   public void setHPP(String hPP) {
       this.hPP = hPP;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("HSRM")
   public Boolean getHSRM() {
       return hSRM;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("HSRM")
   public void setHSRM(Boolean hSRM) {
       this.hSRM = hSRM;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Latitude")
   public Double getLatitude() {
       return latitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Latitude")
   public void setLatitude(Double latitude) {
       this.latitude = latitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Longitude")
   public Double getLongitude() {
       return longitude;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("Longitude")
   public void setLongitude(Double longitude) {
       this.longitude = longitude;
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
    * (Required)
    *
    */
   @JsonProperty("OrganizationGroup")
   public String getOrganizationGroup() {
       return organizationGroup;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OrganizationGroup")
   public void setOrganizationGroup(String organizationGroup) {
       this.organizationGroup = organizationGroup;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Owner")
   public String getOwner() {
       return owner;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Owner")
   public void setOwner(String owner) {
       this.owner = owner;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderName")
   public String getProviderName() {
       return providerName;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderName")
   public void setProviderName(String providerName) {
       this.providerName = providerName;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderAgreement")
   public String getProviderAgreement() {
       return providerAgreement;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderAgreement")
   public void setProviderAgreement(String providerAgreement) {
       this.providerAgreement = providerAgreement;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderNpi")
   public long getProviderNpi() {
       return providerNpi;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderNpi")
   public void setProviderNpi(long providerNpi) {
       this.providerNpi = providerNpi;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderIsPrimaryCare")
   public Boolean getProviderIsPrimaryCare() {
       return providerIsPrimaryCare;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderIsPrimaryCare")
   public void setProviderIsPrimaryCare(Boolean providerIsPrimaryCare) {
       this.providerIsPrimaryCare = providerIsPrimaryCare;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderNetwork")
   public String getProviderNetwork() {
       return providerNetwork;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ProviderNetwork")
   public void setProviderNetwork(String providerNetwork) {
       this.providerNetwork = providerNetwork;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialtyName")
   public String getSpecialtyName() {
       return specialtyName;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialtyName")
   public void setSpecialtyName(String specialtyName) {
       this.specialtyName = specialtyName;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialtyCode")
   public String getSpecialtyCode() {
       return specialtyCode;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("SpecialtyCode")
   public void setSpecialtyCode(String specialtyCode) {
       this.specialtyCode = specialtyCode;
   }
   
   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderServiceStatus")
   public String getProviderServiceStatus() {
       return providerServiceStatus;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderServiceStatus")
   public void setProviderServiceStatus(String providerServiceStatus) {
       this.providerServiceStatus = providerServiceStatus;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderServiceStatusReason")
   public String getProviderServiceStatusReason() {
       return providerServiceStatusReason;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderServiceStatusReason")
   public void setProviderServiceStatusReason(String providerServiceStatusReason) {
       this.providerServiceStatusReason = providerServiceStatusReason;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("VAProviderRelationship")
   public String getVAProviderRelationship() {
       return vAProviderRelationship;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("VAProviderRelationship")
   public void setVAProviderRelationship(String vAProviderRelationship) {
       this.vAProviderRelationship = vAProviderRelationship;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("CreatedBy")
   public String getCreatedBy() {
       return createdBy;
   }

   /**
    *
    * (Required)
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
    * (Required)
    *
    */
   @JsonProperty("DescriptionOfService")
   public String getDescriptionOfService() {
       return descriptionOfService;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DescriptionOfService")
   public void setDescriptionOfService(String descriptionOfService) {
       this.descriptionOfService = descriptionOfService;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DupCheck")
   public String getDupCheck() {
       return dupCheck;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("DupCheck")
   public void setDupCheck(String dupCheck) {
       this.dupCheck = dupCheck;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Limitation")
   public String getLimitation() {
       return limitation;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("Limitation")
   public void setLimitation(String limitation) {
       this.limitation = limitation;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   public String getModifiedBy() {
       return modifiedBy;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("ModifiedBy")
   public void setModifiedBy(String modifiedBy) {
       this.modifiedBy = modifiedBy;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessUnit")
   public String getOwningBusinessUnit() {
       return owningBusinessUnit;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningBusinessUnit")
   public void setOwningBusinessUnit(String owningBusinessUnit) {
       this.owningBusinessUnit = owningBusinessUnit;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningTeam")
   public String getOwningTeam() {
       return owningTeam;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningTeam")
   public void setOwningTeam(String owningTeam) {
       this.owningTeam = owningTeam;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningUser")
   public String getOwningUser() {
       return owningUser;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("OwningUser")
   public void setOwningUser(String owningUser) {
       this.owningUser = owningUser;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderAcceptingNewPatients")
   public Boolean getProviderAcceptingNewPatients() {
       return providerAcceptingNewPatients;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderAcceptingNewPatients")
   public void setProviderAcceptingNewPatients(Boolean providerAcceptingNewPatients) {
       this.providerAcceptingNewPatients = providerAcceptingNewPatients;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderAcceptingVA")
   public Boolean getProviderAcceptingVA() {
       return providerAcceptingVA;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderAcceptingVA")
   public void setProviderAcceptingVA(Boolean providerAcceptingVA) {
       this.providerAcceptingVA = providerAcceptingVA;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderGender")
   public String getProviderGender() {
       return providerGender;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("ProviderGender")
   public void setProviderGender(String providerGender) {
       this.providerGender = providerGender;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("QualityRankingTotalScore")
   public Integer getQualityRankingTotalScore() {
       return qualityRankingTotalScore;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("QualityRankingTotalScore")
   public void setQualityRankingTotalScore(Integer qualityRankingTotalScore) {
       this.qualityRankingTotalScore = qualityRankingTotalScore;
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
   public String getVersionNumber() {
       return versionNumber;
   }

   /**
    *
    * 
    *
    */
   @JsonProperty("VersionNumber")
   public void setVersionNumber(String versionNumber) {
       this.versionNumber = versionNumber;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("WorkHours")
   public String getWorkHours() {
       return workHours;
   }

   /**
    *
    * (Required)
    *
    */
   @JsonProperty("WorkHours")
   public void setWorkHours(String workHours) {
       this.workHours = workHours;
   }
   
   @Override
   public String toString() {
   return new ToStringBuilder(this)
   		.append("providerServiceGuid", providerServiceGuid)
   		.append("addressValidated", addressValidated)
   		.append("careSiteGuid", careSiteGuid)
   		.append("careSiteName", careSiteName)
   		.append("facility", facility)
   		.append("geocoded", geocoded)
   		.append("hPP", hPP)
   		.append("hSRM", hSRM)
   		.append("latitude", latitude)
   		.append("longitude", longitude)
   		.append("modifiedOnDate", modifiedOnDate)
   		.append("name", name)
   		.append("organizationGroup", organizationGroup)
   		.append("owner", owner)
   		.append("providerName", providerName)
   		.append("providerAgreement", providerAgreement)
   		.append("providerNpi", providerNpi)
   		.append("providerIsPrimaryCare", providerIsPrimaryCare)
   		.append("providerNetwork", providerNetwork)
   		.append("specialtyName", specialtyName)
   		.append("specialtyCode", specialtyCode)
   		.append("providerServiceStatus", providerServiceStatus)
   		.append("providerServiceStatusReason", providerServiceStatusReason)
   		.append("vAProviderRelationship", vAProviderRelationship)
   		.append("createdBy", createdBy)
   		.append("createdOn", createdOn)
   		.append("descriptionOfService", descriptionOfService)
   		.append("dupCheck", dupCheck)
   		.append("limitation", limitation)
   		.append("modifiedBy", modifiedBy)
   		.append("owningBusinessUnit", owningBusinessUnit)
   		.append("owningTeam", owningTeam)
   		.append("owningUser", owningUser)
   		.append("providerAcceptingNewPatients", providerAcceptingNewPatients)
   		.append("providerAcceptingVA", providerAcceptingVA)
   		.append("providerGender", providerGender)
   		.append("qualityRankingTotalScore", qualityRankingTotalScore)
   		.append("timeZoneRuleVersionNumber", timeZoneRuleVersionNumber)
   		.append("uTCConversionTimeZoneCode", uTCConversionTimeZoneCode)
   		.append("versionNumber", versionNumber)
   		.append("workHours", workHours)
   		.toString();
   }

}