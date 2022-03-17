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
    @GeneratedValue
    private Long id;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "diagnose_id")
    private Diagnose diagnose;

    @Column
    private String text;
}
