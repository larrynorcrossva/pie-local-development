package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.ProviderOtherIdentifier;

@Repository
public interface ProviderOtherIdentifierRepository extends JpaRepository<ProviderOtherIdentifier, Integer>  {

	public ProviderOtherIdentifier findByProviderOtherIdentifierId(int providerOtherIdentifierId);
	
	public ProviderOtherIdentifier findByOtherIdentifierName(String identifierName);
}