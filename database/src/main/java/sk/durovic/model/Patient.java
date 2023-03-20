package sk.durovic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import sk.durovic.annotations.EntityProcessor;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityProcessor
@Entity
@Table(name = "Patient")
public class Patient extends BaseEntityAbstractClass<String> {

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

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @Setter(AccessLevel.NONE)
    private final List<Contact> contacts = new LinkedList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @Setter(AccessLevel.NONE)
    private final List<Disease> diseases = new LinkedList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @Setter(AccessLevel.NONE)
    private final List<MedicalRecord> medicalRecords = new LinkedList<>();


}
