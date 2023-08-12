package sk.durovic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.durovic.dto.DiagnoseDto;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.DiagnoseEntityService;

@RestController
@RequestMapping("/diagnose")
public class DiagnoseController extends EntityController<DiagnoseDto, Long> {

    public DiagnoseController(DiagnoseEntityService service) {
        super(new RestRequestProcessor<>(service));
    }

    @Override
    protected DiagnoseDto createEntity() {
        return new DiagnoseDto();
    }
}
