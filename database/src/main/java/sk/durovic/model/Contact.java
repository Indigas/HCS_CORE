package sk.durovic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contact extends BaseEntity{

    @Column
    private String fullName;

    @Column
    private String telephone;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
