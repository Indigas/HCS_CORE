package sk.durovic.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import sk.durovic.annotations.Connector;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Connector
@Table(name = "Patient")
public class Patient extends BaseEntityAbstractClass<String>  {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",
        strategy = "sk.durovic.generators.PatientIdGenerator")
    @Setter(AccessLevel.NONE)
    private String id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @OneToMany(mappedBy = "patient")
    private List<Contact> contacts;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @OneToMany(mappedBy = "patient")
    private List<Disease> diseases;

    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecords;

}
