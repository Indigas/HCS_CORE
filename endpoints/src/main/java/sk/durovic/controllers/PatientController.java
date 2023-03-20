package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.dto.PatientDto;
import sk.durovic.manager.factory.EntityManagerCreator;
import sk.durovic.worker.EntityWorkerFactory;

@RestController
@RequestMapping("/patient")
public class PatientController extends EntityController<PatientDto, String>{

    public PatientController(EntityManagerCreator creator) {
        super(EntityWorkerFactory.createEntityWorker(creator));
    }

}
