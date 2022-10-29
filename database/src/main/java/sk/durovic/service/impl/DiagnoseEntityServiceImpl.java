package sk.durovic.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.durovic.repository.DiagnoseRepository;
import sk.durovic.service.DiagnoseService;

@Service
public class DiagnoseServiceImpl extends DiagnoseService {

    @Autowired
    public DiagnoseServiceImpl(DiagnoseRepository repo) {
        this.repo = repo;
    }
}
