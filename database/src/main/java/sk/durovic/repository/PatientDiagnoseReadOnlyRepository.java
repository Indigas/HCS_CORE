package sk.durovic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import sk.durovic.model.Patient_Diagnose;


@NoRepositoryBean
public interface PatientDiagnoseReadOnlyRepository extends JpaRepository<Patient_Diagnose, String> {
}
