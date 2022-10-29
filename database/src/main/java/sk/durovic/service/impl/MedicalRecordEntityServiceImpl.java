package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.MedicalRecordRepository;
import sk.durovic.service.MedicalRecordEntityService;

@Service
public class MedicalRecordEntityServiceImpl extends MedicalRecordEntityService {

    @Autowired
    public MedicalRecordEntityServiceImpl(MedicalRecordRepository repo) {
        this.repo = repo;
    }
}
