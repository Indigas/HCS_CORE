package sk.durovic.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "patient_diagnose")
public class Patient_Diagnose {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",
            strategy = "sk.durovic.generators.PatientIdGenerator")
    private String id;

    @Column(name = "patient_id")
    private String patient;

    @Column(name = "diagnose_id")
    private Long diagnose_id;

    @Column
    private String tag;
    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String description;


}
