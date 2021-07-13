package gov.va.pie.common.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import gov.va.pie.common.utils.CommonConstants;


/**
 * The persistent class for the ProviderSpecialties database table.
 * 
 */
@Entity
@Table(name=CommonConstants.DB_ENV+"ProviderSpecialties_V")
public class ProviderSpecialty implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ProviderSpecialties_Id", unique=true, nullable=false)
	private int providerSpecialtiesId;

	@Column(name="CodedSpecialtyName", length=50)
	private String codedSpecialtyName;

	@Column(name="Created_By", length=30)
	private String createdBy;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="IsPrimaryTaxonomy", nullable=false)
	private boolean isPrimaryTaxonomy;

	@Column(name="Modified_By", length=30)
	private String modifiedBy;

	@Column(name="Modified_Date")
	private Timestamp modifiedDate;

	//bi-directional many-to-one association to Provider
	@ManyToOne
	@JoinColumn(name="Provider_Id_FK", nullable=false)
	private Provider provider;

	public ProviderSpecialty() {
	}

	public int getProviderSpecialtiesId() {
		return this.providerSpecialtiesId;
	}

	public void setProviderSpecialtiesId(int providerSpecialtiesId) {
		this.providerSpecialtiesId = providerSpecialtiesId;
	}

	public String getCodedSpecialtyName() {
		return this.codedSpecialtyName;
	}

	public void setCodedSpecialtyName(String codedSpecialtyName) {
		this.codedSpecialtyName = codedSpecialtyName;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public boolean getIsPrimaryTaxonomy() {
		return this.isPrimaryTaxonomy;
	}

	public void setIsPrimaryTaxonomy(boolean isPrimaryTaxonomy) {
		this.isPrimaryTaxonomy = isPrimaryTaxonomy;
	}

	public String getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Provider getProvider() {
		return this.provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

}