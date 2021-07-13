package gov.va.pie.nvacdwvista.model;

/**
 * Model object for DEA
 * 
 * @author Ablevets
 *
 */
public class DeaModel {

	private String deaNumber;
	private String expirationDate ;
	private String verificationDate ;
	private String verifier;
	private String associatedLocationName ;
	private String hasSchedule;
	private String providerNPI ;
	private String deaStatusReason ;
	private String deaStatus;
	private String inactiveDate;
	private boolean inactiveFlag;
	private String detoxNumber;

	public String getDetoxNumber() {
		return detoxNumber;
	}

	public void setDetoxNumber(String detoxNumber) {
		this.detoxNumber = detoxNumber;
	}

	public String getDeaNumber() {
		return deaNumber;
	}

	public void setDeaNumber(String deaNumber) {
		this.deaNumber = deaNumber;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getVerificationDate() {
		return verificationDate;
	}

	public void setVerificationDate(String verificationDate) {
		this.verificationDate = verificationDate;
	}

	public String getVerifier() {
		return verifier;
	}

	public void setVerifier(String verifier) {
		this.verifier = verifier;
	}

	public String getAssociatedLocationName() {
		return associatedLocationName;
	}

	public void setAssociatedLocationName(String associatedLocationName) {
		this.associatedLocationName = associatedLocationName;
	}

	
	public String getHasSchedule() {
		return hasSchedule;
	}

	public void setHasSchedule(String hasSchedule) {
		this.hasSchedule = hasSchedule;
	}

	public String getProviderNPI() {
		return providerNPI;
	}

	public void setProviderNPI(String providerNPI) {
		this.providerNPI = providerNPI;
	}

	public String getDeaStatusReason() {
		return deaStatusReason;
	}

	public void setDeaStatusReason(String deaStatusReason) {
		this.deaStatusReason = deaStatusReason;
	}
	
	public String getDeaStatus() {
		return deaStatus;
	}
	
	public void setDeaStatus(String deaStatus) {
		this.deaStatus = deaStatus;
	}
	
	public boolean getInactiveFlag() {
		return inactiveFlag;
	}

	public void setInactiveFlag(boolean inactiveFlag) {
		this.inactiveFlag = inactiveFlag;
	}
	
	public String getInactiveDate() {
		return inactiveDate;
	}
	
	public void setInactiveDate(String inactiveDate) {
		this.inactiveDate = inactiveDate;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((deaNumber == null) ? 0 : deaNumber.hashCode());
		result = prime * result + ((providerNPI == null) ? 0 : providerNPI.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof  DeaModel))
			return false;
		DeaModel other = (DeaModel) obj;
		if (deaNumber == null) {
			if (other.deaNumber != null)
				return false;
		} else if (!deaNumber.equals(other.deaNumber))
			return false;
		if (providerNPI == null) {
			if (other.providerNPI != null)
				return false;
		} else if (!providerNPI.equals(other.providerNPI))
			return false;
		return true;
	}

	
}
