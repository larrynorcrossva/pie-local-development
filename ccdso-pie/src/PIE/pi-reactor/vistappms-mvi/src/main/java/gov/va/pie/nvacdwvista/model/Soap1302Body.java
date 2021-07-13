package gov.va.pie.nvacdwvista.model;

import java.util.List;

import gov.va.pie.nvacdwvista.hl7v3.CS;
import gov.va.pie.nvacdwvista.hl7v3.II;
import gov.va.pie.nvacdwvista.hl7v3.INT;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Receiver;
import gov.va.pie.nvacdwvista.hl7v3.MCCIMT000100UV01Sender;
import gov.va.pie.nvacdwvista.hl7v3.TS;

public class Soap1302Body {
	private II id;
	private TS creationTime;
	protected II interactionId;
	protected List<II> profileId;
	protected CS processingCode;
	protected CS processingModeCode;
	protected CS acceptAckCode;
	protected INT sequenceNumber;
	protected MCCIMT000100UV01Receiver receiver;
	protected MCCIMT000100UV01Sender sender;
	protected II regNullFlavor;

	public II getId() {
		return id;
	}

	public void setId(II value) {
		this.id = value;
	}

	public TS getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(TS value) {
		this.creationTime = value;
	}

	public II getInteractionId() {
		return interactionId;
	}

	public void setInteractionId(II value) {
		this.interactionId = value;
	}

	public CS getProcessingCode() {
		return processingCode;
	}

	public void setProcessingCode(CS value) {
		this.processingCode = value;
	}

	public CS getProcessingModeCode() {
		return processingModeCode;
	}

	public void setProcessingModeCode(CS value) {
		this.processingModeCode = value;
	}

	public CS getAcceptAckCode() {
		return acceptAckCode;
	}

	public void setAcceptAckCode(CS value) {
		this.acceptAckCode = value;
	}

	public MCCIMT000100UV01Receiver getReceiver() {
		return this.receiver;
	}

	public void setReceiver(MCCIMT000100UV01Receiver value) {
		this.receiver = value;
	}

	public MCCIMT000100UV01Sender getSender() {
		return sender;
	}

	public void setSender(MCCIMT000100UV01Sender value) {
		this.sender = value;
	}

	public II getRegNullFlavor() {
		return regNullFlavor;
	}

	public void setRegNullFlavor(II regNullFlavor) {
		this.regNullFlavor = regNullFlavor;
	}
	

}
