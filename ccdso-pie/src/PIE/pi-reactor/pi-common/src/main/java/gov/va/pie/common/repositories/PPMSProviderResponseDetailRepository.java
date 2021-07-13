package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.PPMSProviderResponse;
import gov.va.pie.common.entities.PPMSProviderResponseDetail;

@Repository
public interface PPMSProviderResponseDetailRepository extends JpaRepository<PPMSProviderResponseDetail, Integer>  {

	public PPMSProviderResponseDetail[] findByPpmsProviderResponse(PPMSProviderResponse response);
}