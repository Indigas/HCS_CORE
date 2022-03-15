package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientService;

@Service
public class PatientServiceImpl extends PatientService {

    @Autowired
    public PatientServiceImpl(PatientRepository repository) {
        super.repo = repository;
    }
}

