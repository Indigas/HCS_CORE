package sk.durovic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Diagnose {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tag;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @OneToMany(mappedBy = "diagnose", fetch = FetchType.LAZY)
    private List<MedicalRecord> medicalRecords;
}
