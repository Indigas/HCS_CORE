package sk.durovic.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.DiseaseRepository;
import sk.durovic.service.DiseaseService;

@Service
public class DiseaseServiceImpl extends DiseaseService {

    @Autowired
    public DiseaseServiceImpl(DiseaseRepository repo) {
        this.repo = repo;
    }
}
