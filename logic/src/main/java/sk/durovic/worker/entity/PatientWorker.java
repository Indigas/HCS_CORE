package sk.durovic.worker.entity;

import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.Patient;
import sk.durovic.worker.EntityWorker;

import java.util.Collection;

public class PatientWorker implements EntityWorker<Patient, String> {

    private final EntityManagerCreator creator;

    public PatientWorker(EntityManagerCreator creator){
        this.creator = creator;
    }

    @Override
    public Collection<Patient> save(Collection<Patient> entities) {
        return null;
    }

    @Override
    public void delete(Collection<Patient> entities) {

    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void update(Collection<Patient> entity) {

    }

    @Override
    public void updateEntity(String s, Patient entity) {

    }

    @Override
    public Collection<Patient> load(Collection<Patient> entity) {
        return null;
    }

    @Override
    public Patient loadById(String s) {
        return null;
    }
}
