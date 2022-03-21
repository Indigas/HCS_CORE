package sk.durovic.service.impl;

import org.springframework.stereotype.Service;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.repository.PatientDiagnoseRepository;
import sk.durovic.service.PatientDiagnoseService;


@Service
public class PatientDiagnoseServiceImpl
        extends PatientDiagnoseService<Patient_Diagnose, String, PatientDiagnoseRepository<Patient_Diagnose, String>> {


}
