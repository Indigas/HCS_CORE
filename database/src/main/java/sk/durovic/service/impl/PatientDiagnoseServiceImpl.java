package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.repository.PatientDiagnoseRepository;
import sk.durovic.service.PatientDiagnoseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
class PatientDiagnoseServiceImpl
        extends PatientDiagnoseService {

    @Autowired
    public PatientDiagnoseServiceImpl(PatientDiagnoseRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Patient_Diagnose> getDiagnosesByPatientId(String id) {

        Optional<List<Patient_Diagnose>> result = repo.findByPatientId(id);

        return result.orElse(new ArrayList<>());

    }
}
