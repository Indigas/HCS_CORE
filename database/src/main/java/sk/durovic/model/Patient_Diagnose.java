package sk.durovic.model;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "patient_diagnose")
public class Patient_Diagnose {

    @Id
    private String id;

    @Column(name = "patient_id")
    private Patient patient;

    @Column(name = "diagnose_id")
    private Diagnose diagnose;

    @Column
    private String tag;
    @Column
    private String description;


}
