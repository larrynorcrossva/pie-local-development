package gov.va.pie.nvappmscdw.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvappmscdw.model.ProviderOtherIdentifierModel;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * 
 * @author AbleVets
 *
 */
@Service
public class ProviderOtherIdentifiersModelToCDWService {
	
	@Autowired
	private InsertToStaging insertToStaging;
	
	private final String PROVIDEROTHERIDENTIFIER_INSERT = "INSERT INTO " + "App." + CommonConstants.DB_ENV
			+ "ProviderOtherIdentifier_stg_V ("
			+ "ProviderNpi, OtherIdentifierName, IdentifierTypeCode, ProviderIdentifierStatus, ProviderIdentifierStatusReason, PPMSModifiedOn_Date, "
			+ "Created_By, Created_Date, Modified_By, Modified_Date) VALUES ";

	/**
	 * Add Providers' otherIdentifiers Object.
	 * 
	 * @param identifierModelsList - ProviderOtherIdentifierModel object.
	 *                             {@link gov.va.pie.nvappmscdw.model.ProviderOtherIdentifierModel}
	 */
	public void popuplateStaging(List<ProviderOtherIdentifierModel> identifierModelsList) throws RestClientException {
		try {
			insertToStaging.popuplateStaging(
					identifierModelsList, 
					NvaUtils::createInsertStatementForProviderOtherIdentifier, 
					PROVIDEROTHERIDENTIFIER_INSERT,
					"VistA Provider OtherIdentifier");
		} catch(PersistenceException | SQLException e) {
			throw new RestClientException(e.getMessage(), e);
		}
	}
}
