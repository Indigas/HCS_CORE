package sk.durovic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import sk.durovic.annotations.EntityProcessor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityProcessor
@Entity
@Table(name = "Patient")
public class Patient extends BaseEntityAbstractClass<String> implements Serializable {

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
    private transient final List<Contact> contacts = new LinkedList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @Setter(AccessLevel.NONE)
    private transient final List<Disease> diseases = new LinkedList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    @Setter(AccessLevel.NONE)
    private transient final List<MedicalRecord> medicalRecords = new LinkedList<>();


}
