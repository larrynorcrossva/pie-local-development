package gov.va.pie.common.model;

public enum MessageType {
	INFO("Info"), ERROR("Error"), DEBUG("Debug");

	private String value;

	private MessageType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
