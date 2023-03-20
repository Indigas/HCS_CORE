package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.durovic.model.BloodGroup;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PatientDto implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private BloodGroup bloodGroup;
}
