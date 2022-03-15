package sk.durovic.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Disease;


public interface DiseaseRepository extends CrudRepository<Disease, Long> {
}
