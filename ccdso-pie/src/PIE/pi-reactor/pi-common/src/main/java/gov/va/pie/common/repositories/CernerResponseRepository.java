package gov.va.pie.common.repositories;

import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.CernerProvider;
import gov.va.pie.common.entities.CernerResponse;
import gov.va.pie.common.utils.CommonConstants;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CernerResponseRepository extends JpaRepository<CernerResponse, Integer> {
	public List<CernerResponse> findAll();
	
	@Query(value="Select top 1 * from APP."+CommonConstants.DB_ENV + "CernerResponse_V where Status='Success' order by ToDate desc", nativeQuery=true)
	public CernerResponse findLastSuccess();
}
