package sk.durovic.model;

import lombok.Getter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Immutable
@Table(name = "patient_diagnose")
@Subselect("SELECT uuid() as id, p.* FROM patient_diagnose p")
@Getter
public class Patient_Diagnose extends BaseEntityAbstractClass<String> {

    @Id
    private String id;

    @Column(name = "patient_id", insertable = false, updatable = false)
    private String patientId;

    @Column(name = "diagnose_id", insertable = false, updatable = false)
    private Long diagnoseId;

    @Column(insertable = false, updatable = false)
    private String tag;

    @Column(insertable = false, updatable = false)
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Patient_Diagnose;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + patientId.hashCode();
        result = 31 * result + diagnoseId.hashCode();
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
