package gov.va.pie.common.repositories;

import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.CernerProvider;
import gov.va.pie.common.utils.CommonConstants;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CernerProviderRepository extends JpaRepository<CernerProvider, Integer> {
	public List<CernerProvider> findAll();
	
	@Query(value="Select * from App."+CommonConstants.DB_ENV+"Cerner_Providers where coalesce(Modified_Date, Creation_Date) between :startDate and :endDate", nativeQuery=true)
	public List<CernerProvider> findBetweenDates(@Param("startDate") String startDate, @Param("endDate") String endDate);
}
