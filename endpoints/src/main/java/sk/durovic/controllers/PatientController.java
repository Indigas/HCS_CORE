package sk.durovic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.durovic.model.Patient;
import sk.durovic.model.access.PatientEntity;

import java.util.Collection;
import java.util.Collections;

@RestController("/patient")
public class PatientController {

    @GetMapping(value={ "","/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Patient>> getPatients(@PathVariable(required = false) String id){
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Patient>> createPatients(@RequestBody Collection<Patient> patients){
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value={"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePatient(@PathVariable String id, @RequestBody String patient){

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePatients(@RequestBody Collection<String> patients){

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deletePatient(@PathVariable String id){
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deletePatients(@RequestBody Collection<String> patientIds){
    }


}
