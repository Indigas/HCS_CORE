package sk.durovic.dto;

import sk.durovic.model.Patient_Diagnose;

import java.util.ArrayList;
import java.util.List;

public class Patient_DiagnoseDTO {

    private final List<Patient_Diagnose> diagnoses;

    public Patient_DiagnoseDTO(List<Patient_Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Patient_DiagnoseDTO() {
        this.diagnoses = new ArrayList<>();
    }

    public List<Patient_Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void add(Patient_Diagnose diagnose){
        this.diagnoses.add(diagnose);
    }
}
