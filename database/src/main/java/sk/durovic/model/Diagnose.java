package sk.durovic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Diagnose extends BaseEntity {

    @Column
    private String tag;

    @Column
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "diagnose", fetch = FetchType.LAZY)
    private transient List<MedicalRecord> medicalRecords;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj) && obj instanceof Disease;
    }

    @Override
    public int hashCode() {
        int result = tag != null ? tag.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        return result;
    }

    @Override
    public String getParentId() {
        return null;
    }
}
