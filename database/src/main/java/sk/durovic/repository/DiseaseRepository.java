package sk.durovic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Disease;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
}
