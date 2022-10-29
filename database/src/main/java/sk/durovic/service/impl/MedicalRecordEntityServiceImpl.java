package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.MedicalRecordRepository;
import sk.durovic.service.MedicalRecordService;

@Service
public class MedicalRecordServiceImpl extends MedicalRecordService {

    @Autowired
    public MedicalRecordServiceImpl(MedicalRecordRepository repo) {
        this.repo = repo;
    }
}
