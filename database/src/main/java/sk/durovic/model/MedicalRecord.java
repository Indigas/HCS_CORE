package sk.durovic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class MedicalRecord {

    @Id
    private Long id;

    @Column
    private String date;

    @ManyToOne
    private Diagnose diagnose;

    @Column
    private String text;
}
