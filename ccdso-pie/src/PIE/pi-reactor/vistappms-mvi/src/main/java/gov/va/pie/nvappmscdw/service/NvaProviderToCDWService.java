package gov.va.pie.nvappmscdw.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvappmscdw.model.NvaProvider;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * 
 * @author AbleVets
 *
 */
@Service
public class NvaProviderToCDWService {
	
	@Autowired
	private InsertToStaging insertToStaging;

	/**
	 * Add Providers and associated data to tables with details from a NvaProvider
	 * Object.
	 * 
	 * @param responses - ProviderResponses object.
	 *                  {@link gov.va.pie.nvappmscdw.model.NvaProvider}
	 */

	private final String PROVIDER_INSERT =  " Insert  into APP." + CommonConstants.DB_ENV + "NonVAProvider_stg_V "
            + " (ProviderNpi,ProviderType,ProviderFirstName,ProviderMiddleName,ProviderLastName,MainPhone,Fax"
            + "  ,PPMSModifiedOn_Date,ProviderStatus,ProviderStatusReason,Gender) "
            + " values ";
	
	public void popuplateStaging(List<NvaProvider> nvaProvidersList)  throws RestClientException {
		try {
			insertToStaging.popuplateStaging(
					nvaProvidersList, 
					(provider) ->{
						return "(" + provider.getNpi() + ", " + NvaUtils.sqlNullChecker(provider.getProviderType()) + ", " + NvaUtils.sqlNullChecker(provider.getProviderFirstName()) 
						+ ", " + NvaUtils.sqlNullChecker(provider.getProviderMiddleName()) + ", " + NvaUtils.sqlNullChecker(provider.getProviderLastName()) + ", "
						+ NvaUtils.sqlNullChecker(provider.getMainPhone()) + ", " + NvaUtils.sqlNullChecker(provider.getFax()) + ", " 
						+ NvaUtils.sqlNullChecker(provider.getPpmsModifiedOnDate()) + ", " + NvaUtils.sqlNullChecker(provider.getProviderStatus())
						+ ", " + NvaUtils.sqlNullChecker(provider.getProviderStatusReason()) + ", " + NvaUtils.sqlNullChecker(provider.getGender()) + "),";
					},
					PROVIDER_INSERT,
					"VistA Provider");	
		} catch(PersistenceException | SQLException e) {
			throw new RestClientException(e.getMessage(), e);
		}
	}
	
}
