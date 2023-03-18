package sk.durovic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;
import sk.durovic.worker.EntityWorker;
import sk.durovic.worker.entity.ContactWorker;
import sk.durovic.worker.entity.PatientWorker;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/patient")
public class PatientController extends EntityController<Patient, String>{

    public PatientController(EntityManagerCreator creator) {
        super(new PatientWorker(creator));
    }

}
