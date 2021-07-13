package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.PPMSProviderResponse;

@Repository
public interface PPMSProviderResponseRepository extends JpaRepository<PPMSProviderResponse, Integer>  {

	public PPMSProviderResponse findByProviderId(int providerId);
	
}
