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
 * The persistent class for the VistAOutboundMsg database table.
 * 
 */
@Entity
@Table(name = CommonConstants.DB_ENV + "VistA_OutResponse_V")
public class VistaOutResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Vista_OutResponse_Id")
	private int vistaOutResponseId;

	@Column(name = "Created_By")
	private String createdBy;

	@Column(name = "Created_Date")
	private Timestamp createdDate;

	@Column(name = "IsFail")
	private boolean isFail;

	@Column(name = "Modified_By")
	private String modifiedBy;

	@Column(name = "Modified_Date")
	private Timestamp modifiedDate;

	@Column(name = "Outbound_Status_FK")
	private int outboundStatusFK;

	@Column(name = "Response_Message_Text")
	private String responseMessageText;
	
	@Column(name = "VistAResponseCode")
	private String vistaResponseCode;

	// bi-directional many-to-one association to NonVAProvider
	@ManyToOne
	@JoinColumn(name = "NonVAProvider_Id_FK")
	private NonVAProvider nonVaprovider;
	
	@ManyToOne
	@JoinColumn(name = "CareSiteStations_Id_FK")
	private CareSiteStation careSiteStation;

	public VistaOutResponse() {
	}

	public int getVistaOutResponseId() {
		return vistaOutResponseId;
	}

	public void setVistaOutResponseId(int vistaOutResponseId) {
		this.vistaOutResponseId = vistaOutResponseId;
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

	public boolean isFail() {
		return isFail;
	}

	public void setFail(boolean isFail) {
		this.isFail = isFail;
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

	public int getOutboundStatusFK() {
		return outboundStatusFK;
	}

	public void setOutboundStatusFK(int outboundStatusFK) {
		this.outboundStatusFK = outboundStatusFK;
	}

	public String getResponseMessageText() {
		return responseMessageText;
	}

	public void setResponseMessageText(String responseMessageText) {
		this.responseMessageText = responseMessageText;
	}

	public NonVAProvider getNonVaprovider() {
		return nonVaprovider;
	}

	public void setNonVaprovider(NonVAProvider nonVaprovider) {
		this.nonVaprovider = nonVaprovider;
	}

	public String getVistaResponseCode() {
		return vistaResponseCode;
	}

	public void setVistaResponseCode(String vistaResponseCode) {
		this.vistaResponseCode = vistaResponseCode;
	}

	public CareSiteStation getCareSiteStation() {
		return careSiteStation;
	}

	public void setCareSiteStation(CareSiteStation careSiteStation) {
		this.careSiteStation = careSiteStation;
	}
	
}