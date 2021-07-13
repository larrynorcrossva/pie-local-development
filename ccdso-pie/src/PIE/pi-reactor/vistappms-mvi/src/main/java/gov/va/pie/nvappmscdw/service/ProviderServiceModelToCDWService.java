package gov.va.pie.nvappmscdw.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvappmscdw.model.ProviderServiceModel;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * 
 * @author AbleVets
 *
 */
@Service
public class ProviderServiceModelToCDWService {
	
	@Autowired
	private InsertToStaging insertToStaging;
	
	private final String PROVIDERSERVICE_INSERT =  " Insert  into APP." + CommonConstants.DB_ENV + "ProviderService_stg_V "
            + " (ProviderNpi,ProviderNetwork,CareSiteName,CareSiteGuid,CareSiteType,AddressComposite,AddressStreet1,AddressStreet2,AddressCity,AddressState,AddressZip"
            + "  ,SpecialtyCode ,IsPrimaryTaxonomy,Latitude,Longitude,OfficePhone,PPMSModifiedOnDate,ProviderServiceStatus,ProviderServiceStatusReason) "
            + " values ";

	/**
	 * Add Providers and associated data to tables with details from a NvaProvider
	 * Object.
	 * 
	 * @param responses - ProviderResponses object.
	 *                  {@link gov.va.pie.nvappmscdw.model.NvaProvider}
	 */
	public void popuplateStaging(List<ProviderServiceModel> providerServiceList) throws RestClientException {
		try {
			insertToStaging.popuplateStaging(
					providerServiceList, 
					(service)->{
						return "(" + service.getProviderNpi() + ", " + NvaUtils.sqlNullChecker(service.getProviderNetwork()) + ", " + NvaUtils.sqlNullChecker(service.getCareSiteName())
						+ ", " + NvaUtils.sqlNullChecker(service.getCareSiteGuid()) + ", " + NvaUtils.sqlNullChecker(service.getCareSiteType()) + ", " + NvaUtils.sqlNullChecker(service.getAddressComposite())
						+ ", " + NvaUtils.sqlNullChecker(service.getAddressStreet1()) + ", " + NvaUtils.sqlNullChecker(service.getAddressStreet2()) + ", " + NvaUtils.sqlNullChecker(service.getAddressCity()) 
						+ ", " + NvaUtils.sqlNullChecker(service.getAddressState()) + ", " + NvaUtils.sqlNullChecker(service.getAddressZip()) + ", " + NvaUtils.sqlNullChecker(service.getSpecialtyCode())
						+ ", " + NvaUtils.sqlNullChecker(service.getIsPrimaryTaxonomy()) + ", '" + service.getLat() + "', '" + service.getLon() + "' "
						+ ", " + NvaUtils.sqlNullChecker(service.getOfficePhone()) + ", " + NvaUtils.sqlNullChecker(service.getPpmsModifiedOnDate()) + ", " + NvaUtils.sqlNullChecker(service.getProviderServiceStatus()) 
						+ ", " + NvaUtils.sqlNullChecker(service.getProviderServiceStatusReason()) + " ),";
					},
					PROVIDERSERVICE_INSERT,
					"VistA Provider Service");
		} catch(PersistenceException | SQLException e) {
			throw new RestClientException(e.getMessage(), e);
		}
	}
}
