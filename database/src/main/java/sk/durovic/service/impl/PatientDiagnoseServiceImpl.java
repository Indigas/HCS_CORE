package sk.durovic.service.impl;

import org.springframework.stereotype.Service;
import sk.durovic.model.Patient;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.repository.PatientDiagnoseRepository;
import sk.durovic.service.PatientDiagnoseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PatientDiagnoseServiceImpl
        extends PatientDiagnoseService<PatientDiagnoseRepository> {


    @Override
    public List<Patient_Diagnose> getDiagnosesByPatientId(String id) {

        Optional<List<Patient_Diagnose>> result = repo.findByPatientId(id);

        return result.orElse(new ArrayList<>());

    }
}
