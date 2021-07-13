package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;

/**
 * The persistent class for the ProviderServiceCareSite database table.
 * 
 */
@Entity
@Table(name = CommonConstants.DB_ENV + "ProviderServiceCareSite_V")
public class ProviderServiceCareSite implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProviderServiceCareSite_Id")
	private int providerServiceCareSiteId;

	@Column(name = "Created_By")
	private String createdBy;

	@Column(name = "Created_Date")
	private Timestamp createdDate;
	
	@Column(name = "DescriptionOfService")
	private String descriptionOfService;

	@Column(name = "InactiveDate")
	private Date inactiveDate;

	@Column(name = "InactiveFlag")
	private boolean inactiveFlag;
	
	@Column(name = "IsPrimaryCare")
	private String isPrimaryCare;
	
	@Column(name = "IsPrimaryTaxonomy")
	private String isPrimaryTaxonomy;

	@Column(name = "IsProcessed")
	private boolean isProcessed;

	@Column(name = "IsProviderAcceptingNewPatients")
	private String isProviderAcceptingNewPatients;
	
	@Column(name = "IsProviderAcceptingVA")
	private String isProviderAcceptingVA;
	
	@Column(name = "Modified_By")
	private String modifiedBy;

	@Column(name = "Modified_Date")
	private Timestamp modifiedDate;
	
	@Column(name="PPMSCreated_By")
	private String ppmsCreatedBy;

	@Column(name="PPMSCreatedOn_Date")
	private Timestamp ppmsCreatedOnDate;
	
	@Column(name="PPMSModified_By")
	private String ppmsModifiedBy;
	
	@Column(name = "PPMSModifiedOn_Date")
	private Date ppmsModifiedOnDate;

	
	@Column(name = "ProviderNetwork")
	private String providerNetwork;
	
	@Column(name = "ProviderServiceStatus")
	private String providerServiceStatus;

	@Column(name = "ProviderServiceStatusReason")
	private String providerServiceStatusReason;
	
	@Column(name = "VAProviderRelationship")
	private String vaProviderRelationship;
	
	@Column(name = "QualityRankingTotalScore")
	private String qualityRankingTotalScore;
	
	@Column(name = "WorkHours")
	private String workHours;

	// bi-directional many-to-one association to NonVAProvider
	@ManyToOne
	@JoinColumn(name = "NonVAProvider_Id_FK")
	private NonVAProvider nonVaprovider;

	// bi-directional many-to-one association to NonVAProvider
	@ManyToOne
	@JoinColumn(name = "CareSite_Id_FK")
	private CareSite careSite;

	@Column(name = "SpecialityCode")
	private String specialityCode;
	
	@Column(name = "SpecialtyName")
	private String specialtyName;

	public ProviderServiceCareSite() {
	}

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public String getIsPrimaryTaxonomy() {
		return isPrimaryTaxonomy;
	}

	public void setIsPrimaryTaxonomy(String isPrimaryTaxonomy) {
		this.isPrimaryTaxonomy = isPrimaryTaxonomy;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Date getInactiveDate() {
		return this.inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public boolean getInactiveFlag() {
		return this.inactiveFlag;
	}

	public void setInactiveFlag(boolean inactiveFlag) {
		this.inactiveFlag = inactiveFlag;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	public Date getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(Date ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

	public String getProviderServiceStatus() {
		return providerServiceStatus;
	}

	public void setProviderServiceStatus(String providerServiceStatus) {
		this.providerServiceStatus = providerServiceStatus;
	}

	public String getProviderServiceStatusReason() {
		return providerServiceStatusReason;
	}

	public void setProviderServiceStatusReason(String providerServiceStatusReason) {
		this.providerServiceStatusReason = providerServiceStatusReason;
	}

	public NonVAProvider getNonVaprovider() {
		return this.nonVaprovider;
	}

	public void setNonVaprovider(NonVAProvider nonVaprovider) {
		this.nonVaprovider = nonVaprovider;
	}

	public int getProviderServiceCareSiteId() {
		return providerServiceCareSiteId;
	}

	public void setProviderServiceCareSiteId(int providerServiceCareSiteId) {
		this.providerServiceCareSiteId = providerServiceCareSiteId;
	}

	public CareSite getCareSite() {
		return careSite;
	}

	public void setCareSite(CareSite careSite) {
		this.careSite = careSite;
	}

	public boolean isProcessed() {
		return isProcessed;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getDescriptionOfService() {
		return descriptionOfService;
	}

	public void setDescriptionOfService(String descriptionOfService) {
		this.descriptionOfService = descriptionOfService;
	}

	public String getIsPrimaryCare() {
		return isPrimaryCare;
	}

	public void setIsPrimaryCare(String isPrimaryCare) {
		this.isPrimaryCare = isPrimaryCare;
	}

	public String getIsProviderAcceptingNewPatients() {
		return isProviderAcceptingNewPatients;
	}

	public void setIsProviderAcceptingNewPatients(String isProviderAcceptingNewPatients) {
		this.isProviderAcceptingNewPatients = isProviderAcceptingNewPatients;
	}

	public String getIsProviderAcceptingVA() {
		return isProviderAcceptingVA;
	}

	public void setIsProviderAcceptingVA(String isProviderAcceptingVA) {
		this.isProviderAcceptingVA = isProviderAcceptingVA;
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

	public String getPpmsModifiedBy() {
		return ppmsModifiedBy;
	}

	public void setPpmsModifiedBy(String ppmsModifiedBy) {
		this.ppmsModifiedBy = ppmsModifiedBy;
	}

	public String getProviderNetwork() {
		return providerNetwork;
	}

	public void setProviderNetwork(String providerNetwork) {
		this.providerNetwork = providerNetwork;
	}

	public String getVaProviderRelationship() {
		return vaProviderRelationship;
	}

	public void setVaProviderRelationship(String vaProviderRelationship) {
		this.vaProviderRelationship = vaProviderRelationship;
	}

	public String getQualityRankingTotalScore() {
		return qualityRankingTotalScore;
	}

	public void setQualityRankingTotalScore(String qualityRankingTotalScore) {
		this.qualityRankingTotalScore = qualityRankingTotalScore;
	}

	public String getWorkHours() {
		return workHours;
	}

	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

}