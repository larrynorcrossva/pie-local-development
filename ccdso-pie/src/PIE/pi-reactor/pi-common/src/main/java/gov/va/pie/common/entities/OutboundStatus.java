package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the Outbound_Status database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"Outbound_Status_V")
public class OutboundStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="Code")
	private String code;

	@Column(name="Created_By")
	private String created_By;

	@Column(name="Created_Date")
	private Timestamp created_Date;

	@Column(name="Description")
	private String description;

	@Column(name="Modified_By")
	private String modified_By;

	@Column(name="Modified_Date")
	private Timestamp modified_Date;

	//bi-directional many-to-one association to PPMS_OutboundMsg
	@OneToMany(mappedBy="outboundStatus")
	private List<PPMSOutboundMsg> ppmsOutboundMsgs;

	public OutboundStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCreated_By() {
		return this.created_By;
	}

	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}

	public Timestamp getCreated_Date() {
		return this.created_Date;
	}

	public void setCreated_Date(Timestamp created_Date) {
		this.created_Date = created_Date;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getModified_By() {
		return this.modified_By;
	}

	public void setModified_By(String modified_By) {
		this.modified_By = modified_By;
	}

	public Timestamp getModified_Date() {
		return this.modified_Date;
	}

	public void setModified_Date(Timestamp modified_Date) {
		this.modified_Date = modified_Date;
	}

	public List<PPMSOutboundMsg> getPpmsOutboundMsgs() {
		return this.ppmsOutboundMsgs;
	}

	public void setPpmsOutboundMsgs(List<PPMSOutboundMsg> ppmsOutboundMsgs) {
		this.ppmsOutboundMsgs = ppmsOutboundMsgs;
	}

	public PPMSOutboundMsg addPpmsOutboundMsg(PPMSOutboundMsg ppmsOutboundMsg) {
		getPpmsOutboundMsgs().add(ppmsOutboundMsg);
		ppmsOutboundMsg.setOutboundStatus(this);

		return ppmsOutboundMsg;
	}

	public PPMSOutboundMsg removePpmsOutboundMsg(PPMSOutboundMsg ppmsOutboundMsg) {
		getPpmsOutboundMsgs().remove(ppmsOutboundMsg);
		ppmsOutboundMsg.setOutboundStatus(null);

		return ppmsOutboundMsg;
	}

}