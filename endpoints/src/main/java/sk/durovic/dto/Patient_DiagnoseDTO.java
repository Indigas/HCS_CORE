package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Patient_DiagnoseDTO implements Serializable {
    private String id;
    private String patientId;
    private Long diagnoseId;
    private String tag;
    private String description;
}
