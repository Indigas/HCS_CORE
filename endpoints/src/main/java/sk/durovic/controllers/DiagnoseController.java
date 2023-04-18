package sk.durovic.controllers;

import sk.durovic.dto.DiagnoseDto;
import sk.durovic.dto.EntityDto;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.DiagnoseEntityService;

public class DiagnoseController extends EntityController<DiagnoseDto, Long> {

    public DiagnoseController(DiagnoseEntityService service) {
        super(new RestRequestProcessor<>(service));
    }

    @Override
    protected DiagnoseDto createEntity() {
        return new DiagnoseDto();
    }
}
