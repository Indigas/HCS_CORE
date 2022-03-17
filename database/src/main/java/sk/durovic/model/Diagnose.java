package sk.durovic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Diagnose {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String tag;

    @Column
    private String description;

    @OneToMany(mappedBy = "diagnose", fetch = FetchType.LAZY)
    private List<MedicalRecord> medicalRecords;
}
