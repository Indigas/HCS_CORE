package sk.durovic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Patient")
public class Patient extends BaseEntityAbstractClass<String> {

    @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",
        strategy = "sk.durovic.generators.PatientIdGenerator")
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
