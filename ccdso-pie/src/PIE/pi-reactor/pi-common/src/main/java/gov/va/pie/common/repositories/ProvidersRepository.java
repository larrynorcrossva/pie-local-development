package gov.va.pie.common.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.Provider;

@Repository
public interface ProvidersRepository extends JpaRepository<Provider, Integer> {

	@Modifying
	@Query("UPDATE Provider P SET P.ppmsCorrelationId = :ppmsCorrelationId WHERE P.id = :id")
	public int updateCorrelationId(@Param("id") int id, @Param("ppmsCorrelationId") String ppmsCorrelationId);

	public List<Provider> findByStaffSID(int staffSID);

	public Provider findById(int Id);

	@Query("Select count(P) from Provider P where P.isProcessed=1 ")
	public int getUnProcessedProvidersCount();
}
