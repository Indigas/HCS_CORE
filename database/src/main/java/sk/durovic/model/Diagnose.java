package sk.durovic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import sk.durovic.annotations.EntityProcessor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityProcessor
public class Diagnose extends BaseEntity implements Serializable {

    @Column
    private String tag;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @OneToMany(mappedBy = "diagnose", fetch = FetchType.LAZY)
    private transient List<MedicalRecord> medicalRecords;
}
