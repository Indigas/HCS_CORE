package sk.durovic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import sk.durovic.annotations.EntityProcessor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter(AccessLevel.PROTECTED)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityProcessor
public class MedicalRecord extends BaseEntity{

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "diagnose_id")
    @NotNull
    private Diagnose diagnose;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;
}
