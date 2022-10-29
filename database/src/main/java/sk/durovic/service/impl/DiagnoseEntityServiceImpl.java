package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.DiagnoseRepository;
import sk.durovic.service.DiagnoseEntityService;

@Service
public class DiagnoseEntityServiceImpl extends DiagnoseEntityService {

    @Autowired
    public DiagnoseEntityServiceImpl(DiagnoseRepository repo) {
        this.repo = repo;
    }
}
