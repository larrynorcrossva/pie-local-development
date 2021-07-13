package gov.va.pie.nvappmscdw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VistaPharmacyResponseContext {

	@JsonProperty("@odata.context")
	private String context;

	@JsonProperty("value")
	private List<VistaPharmacyResponse> responses;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<VistaPharmacyResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<VistaPharmacyResponse> responses) {
		this.responses = responses;
	}

	@Override
	public String toString() {
		return "VistaPharmacyResponseContext [context=" + context + ", responses=" + responses + "]";
	}

}
