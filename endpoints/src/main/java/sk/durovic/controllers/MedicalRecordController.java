package sk.durovic.controllers;

import sk.durovic.dto.MedicalRecordDto;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.MedicalRecordEntityService;

public class MedicalRecordController extends EntityController<MedicalRecordDto, Long> {

    public MedicalRecordController(MedicalRecordEntityService service) {
        super(new RestRequestProcessor<>(service));
    }

    @Override
    protected MedicalRecordDto createEntity() {
        return new MedicalRecordDto();
    }
}
