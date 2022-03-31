package sk.durovic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MedicalRecord extends BaseEntity{

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
}
