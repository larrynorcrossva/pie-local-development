package gov.va.pie.common.entities;

import java.io.Serializable;
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
 * The persistent class for the PPMS_ProviderResponseDetail database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"PPMS_ProviderResponseDetail_V")
public class PPMSProviderResponseDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int PPMS_ProviderResponseDetail_Id;
	
	@Column(name="Detail_CorrelationId")
	private String detailCorrelationId;
	
	@Column(name="Created_By")
	private String createdBy;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="Modified_By")
	private String modifiedBy;

	@Column(name="Modified_Date")
	private Timestamp modifiedDate;

	@Column(name="Response_Id_Text")
	private String responseIdText;
	
	@Column(name="Response_Header_Text", length=500)
	private String responseHeaderText;
	
	@Column(name="Response_Message_Text", length=500)
	private String responseMessageText;
	
	@Column(name="Response_Type_Text")
	private String responseTypeText;
	
	//bi-directional many-to-one association to PPMS_ProviderResponse
	@ManyToOne
	@JoinColumn(name="PPMS_ProviderResponse_Id_FK")
	private PPMSProviderResponse ppmsProviderResponse;

	@Column(name="IsFail")
	private boolean isFail;

	

	public PPMSProviderResponseDetail() {
	}

	public int getPPMS_ProviderResponseDetail_Id() {
		return this.PPMS_ProviderResponseDetail_Id;
	}

	public void setPPMS_ProviderResponseDetail_Id(int PPMS_ProviderResponseDetail_Id) {
		this.PPMS_ProviderResponseDetail_Id = PPMS_ProviderResponseDetail_Id;
	}

	public String getDetailCorrelationId() {
		return detailCorrelationId;
	}

	public void setDetailCorrelationId(String detailCorrelationId) {
		this.detailCorrelationId = detailCorrelationId;
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

	public String getResponseIdText() {
		return responseIdText;
	}

	public void setResponseIdText(String responseIdText) {
		this.responseIdText = responseIdText;
	}

	public String getResponseHeaderText() {
		return responseHeaderText;
	}

	public void setResponseHeaderText(String responseHeaderText) {
		this.responseHeaderText = responseHeaderText;
	}

	public String getResponseMessageText() {
		return responseMessageText;
	}

	public void setResponseMessageText(String responseMessageText) {
		this.responseMessageText = responseMessageText;
	}

	public String getResponseTypeText() {
		return responseTypeText;
	}

	public void setResponseTypeText(String responseTypeText) {
		this.responseTypeText = responseTypeText;
	}

	public boolean isFail() {
		return isFail;
	}

	public void setFail(boolean isFail) {
		this.isFail = isFail;
	}

	public PPMSProviderResponse getPpmsProviderResponse() {
		return this.ppmsProviderResponse;
	}

	public void setPpmsProviderResponse(PPMSProviderResponse ppmsProviderResponse) {
		this.ppmsProviderResponse = ppmsProviderResponse;
	}

}