package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ContactDto implements Serializable, EntityDto<Long> {

    private Long id;
    private String fullName;
    private String telephone;
    private String notes;
    private String patientId;

}