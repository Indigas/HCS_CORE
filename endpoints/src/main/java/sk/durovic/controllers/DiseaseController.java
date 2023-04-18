package sk.durovic.controllers;

import sk.durovic.dto.DiseaseDto;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.DiseaseEntityService;

public class DiseaseController extends EntityController<DiseaseDto, Long> {

    public DiseaseController(DiseaseEntityService service) {
        super(new RestRequestProcessor<>(service));
    }

    @Override
    protected DiseaseDto createEntity() {
        return new DiseaseDto();
    }
}
