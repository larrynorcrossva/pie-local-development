package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the PPMS_ProviderResponse database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"PPMS_ProviderResponse_V")
public class PPMSProviderResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int PPMS_ProviderResponse_Id;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private Timestamp createdDate;

	@Column(name="modified_by")
	private String modifiedBy;

	@Column(name="modified_date")
	private Timestamp modifiedDate;

	//bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(name="Provider_Id_FK")
	private Provider provider;
	
	@Column(name="Conversation_Number")
	private String conversationNumber;
	
	@Column(name="IsFail")
	private boolean isFail;

	//bi-directional many-to-one association to PPMS_ProviderResponseDetail
	@OneToMany(mappedBy="ppmsProviderResponse")
	private List<PPMSProviderResponseDetail> ppmsProviderResponseDetails;

	public PPMSProviderResponse() {
	}

	public int getPPMS_ProviderResponse_Id() {
		return this.PPMS_ProviderResponse_Id;
	}

	public void setPPMS_ProviderResponse_Id(int PPMS_ProviderResponse_Id) {
		this.PPMS_ProviderResponse_Id = PPMS_ProviderResponse_Id;
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

	public boolean isFail() {
		return isFail;
	}

	public void setFail(boolean isFail) {
		this.isFail = isFail;
	}

	public List<PPMSProviderResponseDetail> getPpmsProviderResponseDetails() {
		return this.ppmsProviderResponseDetails;
	}

	public void setPpmsProviderResponseDetails(List<PPMSProviderResponseDetail> ppmsProviderResponseDetails) {
		this.ppmsProviderResponseDetails = ppmsProviderResponseDetails;
	}
	
	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public String getConversationNumber() {
		return conversationNumber;
	}


	public void setConversationNumber(String conversationNumber) {
		this.conversationNumber = conversationNumber;
	}
	
	public PPMSProviderResponseDetail addPpmsProviderResponseDetail(PPMSProviderResponseDetail ppmsProviderResponseDetail) {
		getPpmsProviderResponseDetails().add(ppmsProviderResponseDetail);
		ppmsProviderResponseDetail.setPpmsProviderResponse(this);

		return ppmsProviderResponseDetail;
	}

	public PPMSProviderResponseDetail removePpmsProviderResponseDetail(PPMSProviderResponseDetail ppmsProviderResponseDetail) {
		getPpmsProviderResponseDetails().remove(ppmsProviderResponseDetail);
		ppmsProviderResponseDetail.setPpmsProviderResponse(null);

		return ppmsProviderResponseDetail;
	}

}