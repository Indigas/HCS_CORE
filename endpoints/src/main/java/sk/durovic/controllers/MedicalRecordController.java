package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.dto.MedicalRecordDto;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.MedicalRecordEntityService;

@RestController
@RequestMapping("/medicalrecord")
public class MedicalRecordController extends EntityController<MedicalRecordDto, Long> {

    public MedicalRecordController(MedicalRecordEntityService service) {
        super(new RestRequestProcessor<>(service));
    }

    @Override
    protected MedicalRecordDto createEntity() {
        return new MedicalRecordDto();
    }
}
