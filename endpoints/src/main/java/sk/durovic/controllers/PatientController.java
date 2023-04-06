package sk.durovic.controllers;

import org.springframework.web.bind.annotation.*;
import sk.durovic.model.Patient;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.PatientEntityService;

@RestController
@RequestMapping("/patient")
public class PatientController extends EntityController<Patient, String>{


    protected PatientController(PatientEntityService service) {
        super(new RestRequestProcessor<>(service));
    }
}
