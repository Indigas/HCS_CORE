package sk.durovic.mapper;

import org.mapstruct.Mapper;
import sk.durovic.dto.Patient_DiagnoseDTO;
import sk.durovic.model.Patient_Diagnose;

@Mapper
public interface PatientDiagnoseMapper extends EntityConverter2Dto<Patient_DiagnoseDTO, Patient_Diagnose> {

}
