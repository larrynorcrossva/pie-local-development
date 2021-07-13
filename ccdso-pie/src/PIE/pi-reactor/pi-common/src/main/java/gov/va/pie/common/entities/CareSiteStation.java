package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the ProviderStations database table.
 * 
 */
@Entity
@Table(name = CommonConstants.DB_ENV + "CareSiteStations_V")
public class CareSiteStation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CareSiteStations_Id")
	private int careSiteStationsId;

	@Column(name="Created_By")
	private String created_By;

	@Column(name="Created_Date")
	private Timestamp created_Date;

	@Column(name="InactiveDate")
	private Date inactiveDate;

	@Column(name="InactiveFlag")
	private boolean inactiveFlag;

	@Column(name="Modified_By")
	private String modified_By;

	@Column(name="Modified_Date")
	private Timestamp modified_Date;

	@Column(name="StationNumber")
	private String stationNumber;

	@Column(name="VISN")
	private String visn;

	//bi-directional many-to-one association to CareSite
	@ManyToOne
	@JoinColumn(name="CareSite_Id_FK")
	private CareSite careSite;
	
	// bi-directional many-to-one association to VistaOutResponse
	@OneToMany(mappedBy = "careSiteStation")
	private List<VistaOutResponse> vistaOutResponses;

	public CareSiteStation() {
	}

	public int getCareSiteStationsId() {
		return careSiteStationsId;
	}

	public void setCareSiteStationsId(int careSiteStationsId) {
		this.careSiteStationsId = careSiteStationsId;
	}

	public String getCreated_By() {
		return created_By;
	}

	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}

	public Timestamp getCreated_Date() {
		return created_Date;
	}

	public void setCreated_Date(Timestamp created_Date) {
		this.created_Date = created_Date;
	}

	public Date getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(Date inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public boolean isInactiveFlag() {
		return inactiveFlag;
	}

	public void setInactiveFlag(boolean inactiveFlag) {
		this.inactiveFlag = inactiveFlag;
	}

	public String getModified_By() {
		return modified_By;
	}

	public void setModified_By(String modified_By) {
		this.modified_By = modified_By;
	}

	public Timestamp getModified_Date() {
		return modified_Date;
	}

	public void setModified_Date(Timestamp modified_Date) {
		this.modified_Date = modified_Date;
	}

	public String getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(String stationNumber) {
		this.stationNumber = stationNumber;
	}

	public String getVisn() {
		return visn;
	}

	public void setVisn(String visn) {
		this.visn = visn;
	}

	public CareSite getCareSite() {
		return careSite;
	}

	public void setCareSite(CareSite careSite) {
		this.careSite = careSite;
	}

	public List<VistaOutResponse> getVistaOutResponses() {
		return this.vistaOutResponses;
	}

	public void setVistaOutResponses(List<VistaOutResponse> vistaOutResponses) {
		this.vistaOutResponses = vistaOutResponses;
	}
	
	public VistaOutResponse addVistaOutboundMsg(VistaOutResponse vistaOutResponse) {
		getVistaOutResponses().add(vistaOutResponse);
		vistaOutResponse.setCareSiteStation(this);
		return vistaOutResponse;
	}

	public VistaOutResponse removeVistaOutResponse(VistaOutResponse vistaOutResponse) {
		getVistaOutResponses().remove(vistaOutResponse);
		vistaOutResponse.setCareSiteStation(null);
		return vistaOutResponse;
	}
}