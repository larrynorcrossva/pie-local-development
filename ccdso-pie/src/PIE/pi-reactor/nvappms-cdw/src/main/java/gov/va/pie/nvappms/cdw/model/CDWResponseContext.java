package gov.va.pie.nvappms.cdw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CDWResponseContext {
	
	@JsonProperty("@odata.context")
	private String context;
	
	@JsonProperty("value")
	private List<CDWResponse> responses;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public List<CDWResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<CDWResponse> responses) {
		this.responses = responses;
	}
	
	

}
