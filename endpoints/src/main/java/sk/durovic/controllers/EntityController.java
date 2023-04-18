package sk.durovic.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import sk.durovic.converter.EventConverterAndCreator;
import sk.durovic.dto.EntityDto;
import sk.durovic.events.Event;
import sk.durovic.events.EventAction;
import sk.durovic.exceptions.IllegalRequest;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.result.Result;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class EntityController<T extends EntityDto<ID>, ID> {

    private final RequestProcessor processor;

    protected EntityController(RequestProcessor processor) {
        this.processor = processor;
    }

    @GetMapping(value={ "","/{id}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<?>> getEntities(@PathVariable(required = false) ID id, @RequestBody(required = false) Collection<T> entities){
        Event event = createEvent(id, entities, EventAction.GET);

        Result result = processor.process(event);

        return new ResponseEntity<>(result.getEntities(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<?>> createEntities(@RequestBody Collection<T> entities){
        Event event = createEvent(null, entities, EventAction.POST);

        Result result = processor.process(event);

        return new ResponseEntity<>(result.getEntities(), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value={"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEntity(@PathVariable ID id, @RequestBody T entity){
        Event event = createEvent(null, List.of(setIdOnEntity(entity, id)), EventAction.PUT);

        Result result = processor.process(event);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEntities(@RequestBody Collection<T> entities){
        Event event = createEvent(null, entities, EventAction.PUT);

        Result result = processor.process(event);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = {"/{id}"}, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEntity(@PathVariable ID id){
        Event event = createEvent(id, Collections.emptyList(), EventAction.PUT);

        Result result = processor.process(event);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEntities(@RequestBody Collection<ID> entitiesId){
        Event event = createEvent(null, Collections.emptyList(), EventAction.PUT);

        Result result = processor.process(event);
    }

    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED, reason = "Request is not allowed")
    @ExceptionHandler({IllegalRequest.class})
    public void handleException(){

    }

    private Event createEvent(ID id, Collection<T> entities, EventAction action){
        Event event = null;

        if(id != null)
            event = EventConverterAndCreator.createRestEvent(List.of(createEntity(id)), action);

        if(event == null)
            event = EventConverterAndCreator.createRestEvent(entities, action);

        return event;
    }

    protected abstract T createEntity();
    private T setIdOnEntity(T entity, ID id){
        if(id != null)
            entity.setId(id);

        return entity;
    };

    private T createEntity(ID id){
        T entity = createEntity();
        return setIdOnEntity(entity, id);
    }
}
