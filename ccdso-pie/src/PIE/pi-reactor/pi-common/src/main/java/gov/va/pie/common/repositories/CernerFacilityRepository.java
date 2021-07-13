package gov.va.pie.common.repositories;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.CernerFacility;
import gov.va.pie.common.entities.CernerProvider;
import gov.va.pie.common.utils.CommonConstants;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CernerFacilityRepository extends JpaRepository<CernerFacility, String> {
	public List<CernerFacility> findAll();
	
	@Query(value="Select * from App."+CommonConstants.DB_ENV+"Cerner_Facilities where coalesce(Modified_Date, Created_Date) between :startDate and :endDate", nativeQuery=true)
	public List<CernerFacility> findBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);
}