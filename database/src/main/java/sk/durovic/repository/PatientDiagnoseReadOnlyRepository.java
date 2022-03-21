package sk.durovic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import sk.durovic.model.Patient_Diagnose;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface PatientDiagnoseReadOnlyRepository<T, ID> extends JpaRepository<T, ID> {
}
