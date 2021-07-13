package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.PPMSNonVAInboundResponse;

@Repository
public interface PPMSNonVAInboundResponseRepository extends JpaRepository<PPMSNonVAInboundResponse, Integer> {

	public PPMSNonVAInboundResponse findByPpmsNonVAInboundResponseId(int Id);
	
	@Query("SELECT r from PPMSNonVAInboundResponse r where " + 
			"  r.ppmsNonVAInboundResponseId = (SELECT MAX(ppmsNonVAInboundResponseId) FROM "
			+ "PPMSNonVAInboundResponse r2 where LOWER(r2.transactionType) = LOWER(:pullStreamName))")
   public PPMSNonVAInboundResponse findlatestResonse(@Param("pullStreamName") String pullStreamName);

}
