package gov.va.pie.nvappmscdw.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VistaPharmacyResponse {

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
	private List<NvaProvider> nvaProviders;

	@JsonProperty("ProviderServices")
	private List<ProviderServiceModel> careSites;

	@JsonProperty("ProviderIdentifiers")
	private List<ProviderOtherIdentifierModel> providerIdentifiers;

	@JsonProperty("DEAs")
	private List<DeaModel> deas;

	@JsonProperty("MedicalEducations")
	private List<ProviderMedicalEducationModel> medicalEducations;

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

	public List<NvaProvider> getNvaProviders() {
		return nvaProviders;
	}

	public void setNvaProviders(List<NvaProvider> nvaProviders) {
		this.nvaProviders = nvaProviders;
	}

	public List<ProviderServiceModel> getCareSites() {
		return careSites;
	}

	public void setCareSites(List<ProviderServiceModel> careSites) {
		this.careSites = careSites;
	}

	public List<ProviderOtherIdentifierModel> getProviderIdentifiers() {
		return providerIdentifiers;
	}

	public void setProviderIdentifiers(List<ProviderOtherIdentifierModel> providerIdentifiers) {
		this.providerIdentifiers = providerIdentifiers;
	}

	public List<DeaModel> getDeas() {
		return deas;
	}

	public void setDeas(List<DeaModel> deas) {
		this.deas = deas;
	}

	public List<ProviderMedicalEducationModel> getMedicalEducations() {
		return medicalEducations;
	}

	public void setMedicalEducations(List<ProviderMedicalEducationModel> medicalEducations) {
		this.medicalEducations = medicalEducations;
	}

	@Override
	public String toString() {
		return "VistaPharmacyResponse [status=" + status + ", totalResults=" + totalResults + ", returnedResults="
				+ returnedResults + ", pageNumber=" + pageNumber + ", totalPages=" + totalPages + ", maxResultsPerPage="
				+ maxResultsPerPage + ", nvaProviders=" + nvaProviders + ", careSites=" + careSites
				+ ", providerIdentifiers=" + providerIdentifiers + ", deas=" + deas + ", medicalEducations="
				+ medicalEducations + "]";
	}

}
