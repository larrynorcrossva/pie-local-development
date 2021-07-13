package gov.va.pie.nvappmscdw.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import gov.va.pie.common.utils.CommonConstants;
import gov.va.pie.nvappmscdw.model.ProviderMedicalEducationModel;
import gov.va.pie.nvappmscdw.utils.NvaUtils;

/**
 * 
 * @author AbleVets
 *
 */
@Service
public class ProviderMedicalEducationModelToCDWService {
	
	@Autowired
	private InsertToStaging insertToStaging;

	private final String PROVIDERMEDICALEDUCATION_INSERT = "INSERT INTO " + "App." + CommonConstants.DB_ENV
			+ "ProviderMedicalEducation_stg_V ("
			+ "ProviderNpi, EducationName, GraduationDate, MedicalEducationStatus, MedicalEducationStatusReason, "
			+ "PPMSModifiedOn_Date, Created_By, Created_Date, Modified_By, Modified_Date) VALUES ";

	/**
	 * Add Providers' ProviderMedicalEducation Objects.
	 * 
	 * @param identifierModelsList - ProviderOtherIdentifierModel object.
	 *                             {@link gov.va.pie.nvappmscdw.model.ProviderMedicalEducationModel}
	 */
	public void popuplateStaging(List<ProviderMedicalEducationModel> providerMedicalEducationModelList) throws RestClientException {
		try {
			insertToStaging.popuplateStaging(
					providerMedicalEducationModelList, 
					NvaUtils::createInsertStatementForProviderMedicalEducation, 
					PROVIDERMEDICALEDUCATION_INSERT,
					"VistA Provider Medical Education");
		} catch(PersistenceException | SQLException e) {
			throw new RestClientException(e.getMessage(), e);
		}
	}

}
