package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.DiseaseRepository;
import sk.durovic.service.DiseaseEntityService;

@Service
public class DiseaseEntityServiceImpl extends DiseaseEntityService {

    @Autowired
    public DiseaseEntityServiceImpl(DiseaseRepository repo) {
        this.repo = repo;
    }
}
