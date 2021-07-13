package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.NonVAProvider;

@Repository
public interface NonVAProviderRepository extends JpaRepository<NonVAProvider, Integer> {

	public NonVAProvider findByNonVAProviderId(int Id);
	
	public NonVAProvider findByProviderNpi(int npi);

}
