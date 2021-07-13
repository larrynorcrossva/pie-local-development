package gov.va.pie.common.model;

public class LogDataFormat {
	
	private String messageType;
	private String messaage;

	public LogDataFormat(String messageType, String messaage) {
		super();
		this.messageType = messageType;
		this.messaage = messaage;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessage(String messageType) {
		this.messageType = messageType;
	}

	public String getMessaage() {
		return messaage;
	}

	public void setMessaage(String messaage) {
		this.messaage = messaage;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	
}
