package gov.va.pie.common.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gov.va.pie.common.entities.PieError;

@Repository
public interface PieErrorRepository extends JpaRepository<PieError, Integer> {

}
