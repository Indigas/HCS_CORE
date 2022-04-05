package sk.durovic.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import sk.durovic.model.Patient_Diagnose;


@NoRepositoryBean
public interface PatientDiagnoseReadOnlyRepository extends Repository<Patient_Diagnose, String> {

}
