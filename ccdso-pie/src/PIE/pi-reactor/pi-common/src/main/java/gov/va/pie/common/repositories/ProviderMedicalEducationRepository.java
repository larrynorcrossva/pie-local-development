package gov.va.pie.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.ProviderMedicalEducation;

@Repository
public interface ProviderMedicalEducationRepository extends JpaRepository<ProviderMedicalEducation, Integer>  {

	@Query("SELECT P FROM ProviderMedicalEducation P join fetch P.nonVaprovider where P.nonVaprovider.nonVAProviderId = :nonVAProviderId") 
	public List<ProviderMedicalEducation> findByNonVaproviderId(@Param("nonVAProviderId") int nonVAProviderId);
	
	@Query("SELECT P FROM ProviderMedicalEducation P where P.educationName = :educationName and P.nonVaprovider.nonVAProviderId = :nonVAProviderId") 
	public ProviderMedicalEducation findByNonVaproviderIdEducationName(@Param("nonVAProviderId") int nonVAProviderId, 
																			 @Param("educationName") String educationName);

}