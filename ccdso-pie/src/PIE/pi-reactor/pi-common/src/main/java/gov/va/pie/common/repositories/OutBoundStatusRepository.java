package gov.va.pie.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.OutboundStatus;

@Repository
public interface OutBoundStatusRepository extends JpaRepository<OutboundStatus, Integer>  {
	
	public List<OutboundStatus> findByCode(String code);


}