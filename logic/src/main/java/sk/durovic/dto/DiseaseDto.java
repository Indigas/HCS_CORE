package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DiseaseDto implements Serializable, EntityDto<Long> {
    private Long id;
    private String name;
    private String description;
    private String patientId;
}
