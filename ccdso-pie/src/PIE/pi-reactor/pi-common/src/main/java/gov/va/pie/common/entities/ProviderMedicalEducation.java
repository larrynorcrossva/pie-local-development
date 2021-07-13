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
 * The persistent class for the ProviderMedicalEducation database table.
 * 
 */
@Entity
@Table(name = CommonConstants.DB_ENV + "ProviderMedicalEducation_V")
public class ProviderMedicalEducation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ProviderMedicalEducation_Id")
	private int providerMedicalEducationId;

	@Column(name = "Created_By")
	private String createdBy;

	@Column(name = "Created_Date")
	private Timestamp createdDate;

	@Column(name = "EducationName")
	private String educationName;

	@Column(name = "GraduationDate")
	private String graduationDate;

	@Column(name = "MedicalEducationStatus")
	private String medicalEducationStatus;

	@Column(name = "MedicalEducationStatusReason")
	private String medicalEducationStatusReason;

	@Column(name = "InactiveDate")
	private Date inactiveDate;

	@Column(name = "InactiveFlag")
	private boolean inactiveFlag;

	@Column(name = "Modified_By")
	private String modifiedBy;

	@Column(name = "Modified_Date")
	private Timestamp modifiedDate;
	
	@Column(name = "PPMSModifiedOn_Date")
	private Date ppmsModifiedOnDate;
	
	// bi-directional many-to-one association to NonVAProvider
	@ManyToOne
	@JoinColumn(name = "NonVAProvider_Id_FK")
	private NonVAProvider nonVaprovider;

	public ProviderMedicalEducation() {
	}

	public int getProviderMedicalEducationId() {
		return providerMedicalEducationId;
	}

	public void setProviderMedicalEducationId(int providerMedicalEducationId) {
		this.providerMedicalEducationId = providerMedicalEducationId;
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
	
	public Date getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(Date ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
	}

	public String getEducationName() {
		return this.educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getGraduationDate() {
		return this.graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getMedicalEducationStatus() {
		return medicalEducationStatus;
	}

	public void setMedicalEducationStatus(String medicalEducationStatus) {
		this.medicalEducationStatus = medicalEducationStatus;
	}

	public String getMedicalEducationStatusReason() {
		return medicalEducationStatusReason;
	}

	public void setMedicalEducationStatusReason(String medicalEducationStatusReason) {
		this.medicalEducationStatusReason = medicalEducationStatusReason;
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

	public NonVAProvider getNonVaprovider() {
		return this.nonVaprovider;
	}

	public void setNonVaprovider(NonVAProvider nonVaprovider) {
		this.nonVaprovider = nonVaprovider;
	}

}