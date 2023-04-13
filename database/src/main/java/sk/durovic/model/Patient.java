package sk.durovic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import sk.durovic.annotations.EntityProcessor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Patient;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (bloodGroup != null ? bloodGroup.hashCode() : 0);
        return result;
    }
}
