package sk.durovic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.durovic.model.Diagnose;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {
}
