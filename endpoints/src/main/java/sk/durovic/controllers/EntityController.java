package sk.durovic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.durovic.processor.RequestProcessor;

import java.util.Collection;
import java.util.Collections;

public abstract class EntityController<T, ID> {

    private final RequestProcessor processor;

    protected EntityController(RequestProcessor processor) {
        this.processor = processor;
    }

    @GetMapping(value={ "","/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<T>> getEntities(@PathVariable(required = false) ID id){

        return null;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<T>> createEntities(@RequestBody Collection<T> entities){
        return new ResponseEntity<>(Collections.EMPTY_LIST, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value={"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEntity(@PathVariable ID id, @RequestBody T entity){

    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEntities(@RequestBody Collection<T> entities){

    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEntity(@PathVariable ID id){
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEntities(@RequestBody Collection<ID> entitiesId){
    }
}
