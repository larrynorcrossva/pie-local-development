package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.CareSite;

@Repository
public interface CareSiteRepository extends JpaRepository<CareSite, Integer>  {
	
	public CareSite findByCareSiteId(int id);
	
	public CareSite findByCareSiteGuid(String guid);
	

}