package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.PatientRepository;
import sk.durovic.service.PatientEntityService;
@Service
class PatientEntityServiceImpl extends PatientEntityService {

    @Autowired
    public PatientEntityServiceImpl(PatientRepository repository) {
        this.repo = repository;
    }


}

