package sk.durovic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ContactDto implements Serializable {
    private long id;
    private String fullName;
    private String telephone;
    private String notes;
}
