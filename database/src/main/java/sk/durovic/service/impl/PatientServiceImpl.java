package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repo;

    @Override
    public Patient save(Patient object) {
        return null;
    }

    @Override
    public void delete(Patient object) {

    }

    @Override
    public void deleteById(String s) {

    }
}

