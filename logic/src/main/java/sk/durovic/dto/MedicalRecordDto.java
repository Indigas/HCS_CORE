package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MedicalRecordDto implements Serializable, EntityDto<Long> {
    private Long id;
    private String date;
    private String text;
    private Long diagnoseId;
    private String patientId;
}
