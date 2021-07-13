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
 * The persistent class for the PPMS_OutboundMsg database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"PPMS_OutboundMsg_V")
public class PPMSOutboundMsg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PPMS_OutboundMsg_Id")
	private int ppmsOutboundMsgId;

	@Column(name="Created_By")
	private String createdBy;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="Modified_By")
	private String modifiedBy;

	@Column(name="Modified_Date")
	private Timestamp modifiedDate;

	@Column(name="Transaction_Count")
	private int transactionCount;

	@Column(name="Transaction_Date")
	private Timestamp transactionDate;

	@Column(name="Transaction_Number")
	private String transactionNumber;

	@Column(name="Conversation_Number")
	private String conversationNumber;
	
	//bi-directional many-to-one association to Outbound_Status
	@ManyToOne
	@JoinColumn(name="Outbound_Status_FK")
	private OutboundStatus outboundStatus;

	//bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(name="Provider_Id_FK")
	private Provider provider;

	public PPMSOutboundMsg() {
	}

	
	public int getPpmsOutboundMsgId() {
		return ppmsOutboundMsgId;
	}


	public void setPpmsOutboundMsgId(int ppmsOutboundMsgId) {
		this.ppmsOutboundMsgId = ppmsOutboundMsgId;
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


	public int getTransactionCount() {
		return transactionCount;
	}


	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}


	public Timestamp getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getTransactionNumber() {
		return transactionNumber;
	}


	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getConversationNumber() {
		return conversationNumber;
	}


	public void setConversationNumber(String conversationNumber) {
		this.conversationNumber = conversationNumber;
	}

	public OutboundStatus getOutboundStatus() {
		return this.outboundStatus;
	}

	public void setOutboundStatus(OutboundStatus outboundStatus) {
		this.outboundStatus = outboundStatus;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

}