package sk.durovic.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class DiagnoseDto implements Serializable, EntityDto<Long> {
    private Long id;
    private String tag;
    private String description;
}
