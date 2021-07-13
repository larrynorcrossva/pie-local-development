package gov.va.pie.nvappms.cdw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CDWResponse {
	
	@JsonProperty("Status")
	private String status;

	@JsonProperty("TotalResults")
	private int totalResults;

	@JsonProperty("ReturnedResults")
	private int returnedResults;

	@JsonProperty("PageNumber")
	private int pageNumber;

	@JsonProperty("TotalPages")
	private int totalPages;

	@JsonProperty("MaxResultsPerPage")
	private int maxResultsPerPage;
	
	@JsonProperty("Providers")
	private List<CDWProvider> cdwProviders;
	
	@JsonProperty("ProviderServices")
	private List<CDWProviderService> providerServices;
	
	@JsonProperty("Agreements")
	private List<CDWAgreement> agreements;
	
	@JsonProperty("CareSites")
	private List<CDWCareSite> careSites;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getReturnedResults() {
		return returnedResults;
	}

	public void setReturnedResults(int returnedResults) {
		this.returnedResults = returnedResults;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getMaxResultsPerPage() {
		return maxResultsPerPage;
	}

	public void setMaxResultsPerPage(int maxResultsPerPage) {
		this.maxResultsPerPage = maxResultsPerPage;
	}

	public List<CDWProvider> getCdwProviders() {
		return cdwProviders;
	}

	public void setCdwProviders(List<CDWProvider> cdwProviders) {
		this.cdwProviders = cdwProviders;
	}

	
	public List<CDWProviderService> getProviderServices() {
		return providerServices;
	}

	public void setProviderServices(List<CDWProviderService> providerServices) {
		this.providerServices = providerServices;
	}

	
	public List<CDWAgreement> getAgreements() {
		return agreements;
	}

	public void setAgreements(List<CDWAgreement> agreements) {
		this.agreements = agreements;
	}

	public List<CDWCareSite> getCareSites() {
		return careSites;
	}

	public void setCareSites(List<CDWCareSite> careSites) {
		this.careSites = careSites;
	}
	
	

}
