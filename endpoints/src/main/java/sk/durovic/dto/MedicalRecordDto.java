package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MedicalRecordDto implements Serializable {
    private long id;
    private String date;
    private String text;
    private long diagnoseId;
    private String patientId;
}
