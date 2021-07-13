package gov.va.pie.nvappmscdw.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvappmscdw.model.DeaModel;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * 
 * @author AbleVets
 *
 */
@Service
public class ProviderDEASchedulePrivilegesModelToCDWService {
	
	@Autowired
	private InsertToStaging insertToStaging;
	
	private final String PROVIDERDEA_INSERT = "INSERT INTO " + "App." + CommonConstants.DB_ENV
			+ "ProviderDEASchedulePrivilege_stg_V ("
			+ "ProviderNpi, DeaNumber, Verifier, VerificationDate, ExpirationDate, AssociatedLocationName, "
			+ "HasScheduleII, HasScheduleIINonNarcotic, HasScheduleIII, HasScheduleIIINonNarcotic, HasScheduleIV, "
			+ "HasScheduleV, DEAStatus, DEAStatusReason, PPMSModifiedOn_Date, "
			+ "Created_By, Created_Date, Modified_By, Modified_Date) VALUES ";

	/**
	 * Add Providers' DEA Objects.
	 * 
	 * @param responses
	 *            - ProviderResponses object.
	 *            {@link gov.va.pie.nvappmscdw.model.DeaModel}
	 */
	public void popuplateStaging(List<DeaModel> deaModelsList) throws RestClientException {
		
		try {
			insertToStaging.popuplateStaging(
					deaModelsList, 
					NvaUtils::createInsertStatementForProviderDea, 
					PROVIDERDEA_INSERT,
					"VistA Provider DEA");
		} catch(PersistenceException | SQLException e) {
			throw new RestClientException(e.getMessage(), e);
		}
	}

}
