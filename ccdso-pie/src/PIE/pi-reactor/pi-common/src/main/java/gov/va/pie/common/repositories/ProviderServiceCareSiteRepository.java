package gov.va.pie.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.ProviderServiceCareSite;

@Repository
public interface ProviderServiceCareSiteRepository extends JpaRepository<ProviderServiceCareSite, Integer> {
	
	@Query("SELECT P FROM ProviderServiceCareSite P join fetch P.nonVaprovider where P.nonVaprovider.nonVAProviderId = :nonVAProviderId") 
	public List<ProviderServiceCareSite> findByNonVAProviderId(@Param("nonVAProviderId") int nonVAProviderId);

	@Query("SELECT P FROM ProviderServiceCareSite P join fetch P.careSite where P.careSite.careSiteId = :careSiteId" ) 
	public List<ProviderServiceCareSite> findByCareSiteId(@Param("careSiteId") int careSiteId);
	
	@Query("SELECT P FROM ProviderServiceCareSite P where P.careSite.careSiteId = :careSiteId and P.nonVaprovider.nonVAProviderId = :nonVAProviderId" ) 
	public ProviderServiceCareSite findByNonVAProviderIdCareSiteId(@Param("nonVAProviderId") int nonVAProviderId, @Param("careSiteId") int careSiteId);


}
