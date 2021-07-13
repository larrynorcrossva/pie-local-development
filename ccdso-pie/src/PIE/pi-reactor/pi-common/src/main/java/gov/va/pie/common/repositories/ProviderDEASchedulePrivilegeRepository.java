package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.ProviderDEASchedulePrivilege;

@Repository
public interface ProviderDEASchedulePrivilegeRepository extends JpaRepository<ProviderDEASchedulePrivilege, Integer>  {

	@Query("SELECT P FROM ProviderDEASchedulePrivilege P join fetch P.nonVaprovider where P.nonVaprovider.nonVAProviderId = :nonVAProviderId") 
	public ProviderDEASchedulePrivilege findByNonVaproviderId(@Param("nonVAProviderId") int nonVAProviderId);
	
	public ProviderDEASchedulePrivilege findByDeaNumber(String deaNumber);
}