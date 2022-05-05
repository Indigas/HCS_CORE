package sk.durovic.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sk.durovic.annotations.EntityProcessor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityProcessor
public class Contact extends BaseEntity{

    @Column
    private String fullName;

    @Column
    private String telephone;

    @Column
    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;

}
