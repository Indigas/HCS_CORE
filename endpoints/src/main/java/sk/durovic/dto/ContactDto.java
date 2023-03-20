package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto implements Serializable {
    private long id;
    private String fullName;
    private String telephone;
    private String notes;
    private String patientId;
}
