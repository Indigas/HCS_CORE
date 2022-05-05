package sk.durovic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;
import sk.durovic.annotations.EntityProcessor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityProcessor
public class Diagnose extends BaseEntity{

    @Column
    private String tag;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @OneToMany(mappedBy = "diagnose", fetch = FetchType.LAZY)
    private List<MedicalRecord> medicalRecords;
}
