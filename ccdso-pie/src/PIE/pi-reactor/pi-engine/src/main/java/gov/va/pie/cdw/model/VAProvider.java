package gov.va.pie.cdw.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("provider")
public class VAProvider implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@XmlElement
	private int id;
	
	@XmlElement
	private int staffSid;
	
	@XmlElement
	private String staffIEN;
	
	@XmlElement
	private String npi;
	
	@XmlElement
	private String dea;
	 
	@XmlElement
	private String staffName;
	
	@XmlElement
	private String lastName;
	
	@XmlElement
	private String firstName;
	
	@XmlElement
	private String middleName;
	
	@XmlElement
	private String officePhone;
	
	@XmlElement
	private String faxNumber;
	
	@XmlElement
	private String emailAddress;
	
	@XmlElement
	private String gender;
	
	@XmlElement
	private String terminationDate;
	
	@XmlElement
	private String x12Code;
	
	@XmlElement
	private int sta3n;
	
	@XmlElement
	private String sta6a;
	
	@XmlElement
	private int visn ;
	
	@XmlElement
	private int region ;
	
	@XmlElement
	private String streetAddress1;
	
	@XmlElement
	private String streetAddress2;
	
	@XmlElement
	private String city;
	
	@XmlElement
	private String state;
	
	@XmlElement
	private String zip;
	
	@XmlElement
	private String createdBy;
	
	@XmlElement	
	private Date createdDate;
	
	@XmlElement
	private String modifiedBy;
	
	@XmlElement
	private Date modifiedDate;
	
	@XmlElement
	private boolean isProcessed;
	
	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	public int getStaffSid() {
		return staffSid;
	}




	public void setStaffSid(int staffSid) {
		this.staffSid = staffSid;
	}




	public String getStaffIEN() {
		return staffIEN;
	}




	public void setStaffIEN(String staffIEN) {
		this.staffIEN = staffIEN;
	}




	public String getNpi() {
		return npi;
	}




	public void setNpi(String npi) {
		this.npi = npi;
	}




	public String getDea() {
		return dea;
	}




	public void setDea(String dea) {
		this.dea = dea;
	}




	public String getStaffName() {
		return staffName;
	}




	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getFirstName() {
		return firstName;
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getMiddleName() {
		return middleName;
	}




	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}




	public String getOfficePhone() {
		return officePhone;
	}




	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}




	public String getFaxNumber() {
		return faxNumber;
	}




	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}




	public String getEmailAddress() {
		return emailAddress;
	}




	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}




	public String getGender() {
		return gender;
	}




	public void setGender(String gender) {
		this.gender = gender;
	}




	public String getTerminationDate() {
		return terminationDate;
	}




	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}




	public String getX12Code() {
		return x12Code;
	}




	public void setX12Code(String x12Code) {
		this.x12Code = x12Code;
	}




	public int getSta3n() {
		return sta3n;
	}




	public void setSta3n(int sta3n) {
		this.sta3n = sta3n;
	}




	public String getSta6a() {
		return sta6a;
	}




	public void setSta6a(String sta6a) {
		this.sta6a = sta6a;
	}




	public int getVisn() {
		return visn;
	}




	public void setVisn(int visn) {
		this.visn = visn;
	}




	public int getRegion() {
		return region;
	}




	public void setRegion(int region) {
		this.region = region;
	}




	public String getStreetAddress1() {
		return streetAddress1;
	}




	public void setStreetAddress1(String streetAddress1) {
		this.streetAddress1 = streetAddress1;
	}




	public String getStreetAddress2() {
		return streetAddress2;
	}




	public void setStreetAddress2(String streetAddress2) {
		this.streetAddress2 = streetAddress2;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getState() {
		return state;
	}




	public void setState(String stateName) {
		this.state = stateName;
	}




	public String getZip() {
		return zip;
	}




	public void setZip(String zipCode) {
		this.zip = zipCode;
	}




	public String getCreatedBy() {
		return createdBy;
	}




	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public Date getCreatedDate() {
		return createdDate;
	}




	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	public String getModifiedBy() {
		return modifiedBy;
	}




	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}




	public Date getModifiedDate() {
		return modifiedDate;
	}




	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

	public boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(boolean processed) {
		this.isProcessed = processed;
	}
	
	/*
	* Intentionally blank
	*/ 

	public VAProvider() {
		
	}
	
	public VAProvider(int id, int staffSid, String staffIEN, String npi, String dea, String staffName, String lastName,
			String firstName, String middleName, String officePhone, String faxNumber, String emailAddress,
			String gender, String terminationDate, String x12Code, int sta3n, String sta6a, int visn,
			int region, String streetAddress1, String streetAddress2, String city, String state, String zip,
			String createdBy, Date createdDate, String modifiedBy, Date modifiedDate, boolean processed) {
		super();
		this.id = id;
		this.staffSid = staffSid;
		this.staffIEN = staffIEN;
		this.npi = npi;
		this.dea = dea;
		this.staffName = staffName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.officePhone = officePhone;
		this.faxNumber = faxNumber;
		this.emailAddress = emailAddress;
		this.gender = gender;
		this.terminationDate = terminationDate;
		this.x12Code = x12Code;
		this.sta3n = sta3n;
		this.sta6a = sta6a;
		this.visn = visn;
		this.region = region;
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.isProcessed = processed;
	}

	
	public  String toXMLString() {
		XStream xs = new XStream();
		xs.alias("provider", VAProvider.class);
		return xs.toXML(this);
	}
		
	/**
	* Convert from XML to object
	* @param xml
	* @return
	*/
	public  static Object fromXMLToJava(String xml){
		XStream xs = new XStream();
		//xs.alias("provider", VAProvider.class);

		return xs.fromXML(xml);
	}
}
