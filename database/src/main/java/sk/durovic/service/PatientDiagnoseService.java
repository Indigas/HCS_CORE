package sk.durovic.service;

import org.springframework.beans.factory.annotation.Autowired;
import sk.durovic.repository.PatientDiagnoseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class PatientDiagnoseService<T, ID, R extends PatientDiagnoseRepository<T, ID>> {

    @Autowired
    protected R repo;

    public List<T> getDiagnosesByPatientId(ID id){
        Optional<List<T>> result = repo.findByPatientId(id);

        return result.orElse(new ArrayList<>());
    }

}
