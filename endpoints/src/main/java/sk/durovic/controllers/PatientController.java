package sk.durovic.controllers;

import org.springframework.web.bind.annotation.*;
import sk.durovic.dto.PatientDto;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.model.Patient;
import sk.durovic.worker.EntityWorkerFactory;

@RestController
@RequestMapping("/patient")
public class PatientController extends EntityController<PatientDto, String>{

    public PatientController(EntityManagerCreator creator) {
        super(EntityWorkerFactory.createEntityWorker(creator));
    }

}
