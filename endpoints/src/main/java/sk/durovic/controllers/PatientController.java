package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.model.Patient;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.PatientEntityService;

@RestController
@RequestMapping("/patient")
public class PatientController extends EntityController<Patient, String>{


    protected PatientController(PatientEntityService service) {
        super(new RestRequestProcessor<>(service));
    }
}
