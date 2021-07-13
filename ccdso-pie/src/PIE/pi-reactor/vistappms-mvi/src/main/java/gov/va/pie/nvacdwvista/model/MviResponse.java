package gov.va.pie.nvacdwvista.model;

public class MviResponse {

	private String nvaProviderId;
	private String station;
	private String actionCode;
	private String providerServicesCareSiteId;
	private String careSiteId;
	private String message;
	private String vistaCode;
	private String vistaOutResponseId;

	public String getNvaProviderId() {
		return nvaProviderId;
	}

	public void setNvaProviderId(String nvaProviderId) {
		this.nvaProviderId = nvaProviderId;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getProviderServicesCareSiteId() {
		return providerServicesCareSiteId;
	}

	public void setProviderServicesCareSiteId(String providerServicesCareSiteId) {
		this.providerServicesCareSiteId = providerServicesCareSiteId;
	}

	public String getCareSiteId() {
		return careSiteId;
	}

	public void setCareSiteId(String careSiteId) {
		this.careSiteId = careSiteId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVistaCode() {
		return vistaCode;
	}

	public void setVistaCode(String vistaCode) {
		this.vistaCode = vistaCode;
	}

	public String getVistaOutResponseId() {
		return vistaOutResponseId;
	}

	public void setVistaOutResponseId(String vistaOutResponseId) {
		this.vistaOutResponseId = vistaOutResponseId;
	}

}
