package gov.va.pie.nvappmscdw.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model object for ProviderMedicalEducation
 * 
 * @author Ablevets
 *
 */
public class ProviderMedicalEducationModel {

	@JsonProperty("ProviderNPI")
	private Long providerNPI;

	@JsonProperty("EducationName")
	private String educationName;

	@JsonProperty("GraduationDate")
	private String graduationDate;

	@JsonProperty("MedicalEducationStatus")
	private String medicalEducationStatus;

	@JsonProperty("MedicalEducationStatusReason")
	private String medicalEducationStatusReason;

	@JsonProperty("ModifiedOnDate")
	private String ppmsModifiedOnDate;

	public Long getProviderNPI() {
		return providerNPI;
	}

	public void setProviderNPI(Long providerNPI) {
		this.providerNPI = providerNPI;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getPpmsModifiedOnDate() {
		return ppmsModifiedOnDate;
	}

	public void setPpmsModifiedOnDate(String ppmsModifiedOnDate) {
		this.ppmsModifiedOnDate = ppmsModifiedOnDate;
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
}
