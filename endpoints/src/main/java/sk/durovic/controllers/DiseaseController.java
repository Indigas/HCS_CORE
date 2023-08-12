package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.dto.DiseaseDto;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.DiseaseEntityService;

@RestController
@RequestMapping("/disease")
public class DiseaseController extends EntityController<DiseaseDto, Long> {

    public DiseaseController(DiseaseEntityService service) {
        super(new RestRequestProcessor<>(service));
    }

    @Override
    protected DiseaseDto createEntity() {
        return new DiseaseDto();
    }
}
