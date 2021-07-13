package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the Providers database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"Providers_V")

public class Provider implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;

	@Column(name="City")
	private String city;

	@Column(name="Created_By")
	private String created_By;

	@Column(name="Created_Date")
	private Timestamp created_Date;

	@Column(name="DEA")
	private String dea;

	@Column(name="EmailAddress")
	private String emailAddress;

	@Column(name="FaxNumber")
	private String faxNumber;

	@Column(name="FirstName")
	private String firstName;

	@Column(name="Gender")
	private String gender;

	@Column(name="IsProcessed")
	private boolean isProcessed;

	@Column(name="LastName")
	private String lastName;

	@Column(name="MiddleName")
	private String middleName;

	@Column(name="Modified_By")
	private String modified_By;

	@Column(name="Modified_Date")
	private Timestamp modified_Date;

	@Column(name="NPI")
	private String npi;

	@Column(name="OfficePhone")
	private String officePhone;

	@Column(name="PPMS_CorrelationId")
	private String ppmsCorrelationId;

	@Column(name="Region")
	private short region;

	@Column(name="Sta3n")
	private short sta3n;

	@Column(name="Sta6a")
	private String sta6a;

	@Column(name="StaffIEN")
	private String staffIEN;

	@Column(name="StaffName")
	private String staffName;

	@Column(name="StaffSID")
	private int staffSID;

	@Column(name="State")
	private String state;

	@Column(name="StreetAddress1")
	private String streetAddress1;

	@Column(name="StreetAddress2")
	private String streetAddress2;

	@Column(name="TerminationDate")
	private String terminationDate;

	@Column(name="VISN")
	private short visn;

	@Column(name="X12Code")
	private String x12Code;

	@Column(name="Zip")
	private String zip;

	//bi-directional many-to-one association to PPMS_OutboundMsg
	@OneToMany(mappedBy="provider")
	private List<PPMSOutboundMsg> ppmsOutboundMsgs;

	//bi-directional many-to-one association to PPMS_ProviderResponseDetail
	@OneToMany(mappedBy="provider")
	private List<PPMSProviderResponse> ppmsProviderResponses;

	public Provider() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreated_By() {
		return this.created_By;
	}

	public void setCreated_By(String created_By) {
		this.created_By = created_By;
	}

	public Timestamp getCreated_Date() {
		return this.created_Date;
	}

	public void setCreated_Date(Timestamp created_Date) {
		this.created_Date = created_Date;
	}

	public String getDea() {
		return this.dea;
	}

	public void setDea(String dea) {
		this.dea = dea;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFaxNumber() {
		return this.faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public boolean getIsProcessed() {
		return this.isProcessed;
	}

	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getModified_By() {
		return this.modified_By;
	}

	public void setModified_By(String modified_By) {
		this.modified_By = modified_By;
	}

	public Timestamp getModified_Date() {
		return this.modified_Date;
	}

	public void setModified_Date(Timestamp modified_Date) {
		this.modified_Date = modified_Date;
	}

	public String getNpi() {
		return this.npi;
	}

	public void setNpi(String npi) {
		this.npi = npi;
	}

	public String getOfficePhone() {
		return this.officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	

	public String getPpmsCorrelationId() {
		return ppmsCorrelationId;
	}

	public void setPpmsCorrelationId(String ppmsCorrelationId) {
		this.ppmsCorrelationId = ppmsCorrelationId;
	}

	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	public short getRegion() {
		return this.region;
	}

	public void setRegion(short region) {
		this.region = region;
	}

	public short getSta3n() {
		return this.sta3n;
	}

	public void setSta3n(short sta3n) {
		this.sta3n = sta3n;
	}

	public String getSta6a() {
		return this.sta6a;
	}

	public void setSta6a(String sta6a) {
		this.sta6a = sta6a;
	}

	public String getStaffIEN() {
		return this.staffIEN;
	}

	public void setStaffIEN(String staffIEN) {
		this.staffIEN = staffIEN;
	}

	public String getStaffName() {
		return this.staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public int getStaffSID() {
		return this.staffSID;
	}

	public void setStaffSID(int staffSID) {
		this.staffSID = staffSID;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreetAddress1() {
		return this.streetAddress1;
	}

	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}

	public String getStreetAddress2() {
		return this.streetAddress2;
	}

	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}

	public String getTerminationDate() {
		return this.terminationDate;
	}

	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}

	public short getVisn() {
		return this.visn;
	}

	public void setVisn(short visn) {
		this.visn = visn;
	}

	public String getX12Code() {
		return this.x12Code;
	}

	public void setX12Code(String x12Code) {
		this.x12Code = x12Code;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<PPMSOutboundMsg> getPpmsOutboundMsgs() {
		return this.ppmsOutboundMsgs;
	}

	public void setPpmsOutboundMsgs(List<PPMSOutboundMsg> ppmsOutboundMsgs) {
		this.ppmsOutboundMsgs = ppmsOutboundMsgs;
	}

	public PPMSOutboundMsg addPpmsOutboundMsg(PPMSOutboundMsg ppmsOutboundMsg) {
		getPpmsOutboundMsgs().add(ppmsOutboundMsg);
		ppmsOutboundMsg.setProvider(this);

		return ppmsOutboundMsg;
	}

	public PPMSOutboundMsg removePpmsOutboundMsg(PPMSOutboundMsg ppmsOutboundMsg) {
		getPpmsOutboundMsgs().remove(ppmsOutboundMsg);
		ppmsOutboundMsg.setProvider(null);

		return ppmsOutboundMsg;
	}

	public List<PPMSProviderResponse> getPpmsProviderResponses() {
		return this.ppmsProviderResponses;
	}

	public void setPpmsProviderResponses(List<PPMSProviderResponse> ppmsProviderResponses) {
		this.ppmsProviderResponses = ppmsProviderResponses;
	}

	public PPMSProviderResponse addPpmsProviderResponses(PPMSProviderResponse ppmsProviderResponse) {
		getPpmsProviderResponses().add(ppmsProviderResponse);
		ppmsProviderResponse.setProvider(this);

		return ppmsProviderResponse;
	}

	public PPMSProviderResponse removePpmsProviderResponse(PPMSProviderResponse ppmsProviderResponse) {
		getPpmsProviderResponses().remove(ppmsProviderResponse);
		ppmsProviderResponse.setProvider(null);

		return ppmsProviderResponse;
	}

}