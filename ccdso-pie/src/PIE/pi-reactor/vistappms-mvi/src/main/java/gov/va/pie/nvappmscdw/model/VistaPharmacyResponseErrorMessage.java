package gov.va.pie.nvappmscdw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VistaPharmacyResponseErrorMessage {
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("message")
	private String message;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "VistaPharmacyResponseError [code=" + code + ", message=" + message + "]";
	}
}
