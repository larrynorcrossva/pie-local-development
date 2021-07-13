package gov.va.pie.common.entities;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
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
@Table(name=CommonConstants.DB_ENV+"Cerner_Facilities")
public class CernerFacility implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="CareSiteGuid")
	private String id;
	
	@Column(name="CareSiteName")
	private String name;
	
	
	@Column(name="AddressStreet1")
	private String addressStreet1;
	
	@Column(name="AddressStreet2")
	private String addressStreet2;
	
	
	@Column(name="City")
	private String city;
	
	@Column(name="State")
	private String state;
	
	@Column(name="ZipCode")
	private String zipCode;
	
	@Column(name="Country")
	private String country;
	
	@Column(name="MainSitePhone")
	private String mainSitePhone;
	
	@Column(name="CareSiteFax")
	private String careSiteFax;
	
	@Column(name="CareSiteStatusReason")
	private String careSiteStatusReason;
	
	public List<String> toFieldList(){
		ArrayList<String> l = new ArrayList<String>();
		l.add(id);
		l.add(uniqueName());
		l.add(removeCommas(addressStreet1));
		l.add(removeCommas(addressStreet2));
		l.add(removeCommas(city));
		l.add(state);
		l.add(zipCode);
		l.add(country);
		l.add(removeCommas(mainSitePhone));
		l.add(removeCommas(careSiteFax));
		l.add(careSiteStatusReason);
		return l;
	}
	
	private String uniqueName() {
		return removeCommas(name) + " " + state + "_" + id.substring(0,8); 
	}
	
	private String removeCommas(String s) {
		if(s==null) { return null; }
		String pattern = ",";
		return s.replaceAll(pattern, "");
	}
	
	
}
