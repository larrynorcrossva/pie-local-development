package gov.va.pie.nvappmscdw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VistaPharmacyResponseError {

	@JsonProperty("error")
	private VistaPharmacyResponseErrorMessage error;

	public VistaPharmacyResponseErrorMessage getError() {
		return error;
	}

	public void setError(VistaPharmacyResponseErrorMessage error) {
		this.error = error;
	}	

}
