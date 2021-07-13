package gov.va.pie.nvacdwvista.model;

public class SpecialityModel {
	private String specialityCode;
	private boolean specialityActive;
	private String specialityinActiveDate;

	public String getSpecialityCode() {
		return specialityCode;
	}

	public void setSpecialityCode(String specialityCode) {
		this.specialityCode = specialityCode;
	}

	public boolean getSpecialityActive() {
		return specialityActive;
	}

	public void setSpecialityActive(boolean specialityActive) {
		this.specialityActive = specialityActive;
	}

	public String getSpecialityinActiveDate() {
		return specialityinActiveDate;
	}

	public void setSpecialityinActiveDate(String specialityinActiveDate) {
		this.specialityinActiveDate = specialityinActiveDate;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (specialityActive ? 1231 : 1237);
		result = prime * result + ((specialityCode == null) ? 0 : specialityCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecialityModel other = (SpecialityModel) obj;
		if (specialityActive != other.specialityActive)
			return false;
		if (specialityCode == null) {
			if (other.specialityCode != null)
				return false;
		} else if (!specialityCode.equals(other.specialityCode))
			return false;
		return true;
	}

	
	
	
}
