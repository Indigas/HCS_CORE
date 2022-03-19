package sk.durovic.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "patient_diagnose")
@Subselect("SELECT uuid() as id, p.* FROM patient_diagnose p")
public class Patient_Diagnose {

    @Id
    private String id;

    @Column(name = "patient_id", insertable = false, updatable = false)
    private String patient_id;

    @Column(name = "diagnose_id", insertable = false, updatable = false)
    private Long diagnose_id;

    @Column(insertable = false, updatable = false)
    private String tag;

    @Column(insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String description;


}
