package sk.durovic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MedicalRecord extends BaseEntity {

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "diagnose_id")
    @NotNull
    private Diagnose diagnose;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String text;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof MedicalRecord;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + diagnose.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + getId().hashCode();
        return result;
    }

    @Override
    public String getParentId() {
        return patient.getParentId() + ":" + diagnose.getId();
    }
}
