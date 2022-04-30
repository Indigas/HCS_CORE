package sk.durovic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Disease extends BaseEntity{

    @Column
    private String name;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
