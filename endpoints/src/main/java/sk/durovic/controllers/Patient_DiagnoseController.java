package sk.durovic.controllers;

import sk.durovic.actions.ViewModelEntityRequestAction;
import sk.durovic.dto.Patient_DiagnoseDTO;
import sk.durovic.processor.RequestProcessor;
import sk.durovic.processor.RestRequestProcessor;
import sk.durovic.service.PatientDiagnoseService;

public class Patient_DiagnoseController extends EntityController<Patient_DiagnoseDTO, Object> {

    public Patient_DiagnoseController(PatientDiagnoseService service) {
        super(new RestRequestProcessor<>(new ViewModelEntityRequestAction<>(service)));
    }

    @Override
    protected Patient_DiagnoseDTO createEntity() {
        return new Patient_DiagnoseDTO();
    }


}
