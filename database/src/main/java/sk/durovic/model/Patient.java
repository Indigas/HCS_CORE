package sk.durovic.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Patient")
public class Patient {

    @Id
    private String id;

    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;

    @ManyToMany
    private List<Contact> contacts;

    @Enumerated(EnumType.STRING)
    private BloodGroup bloodGroup;

    @OneToMany
    private List<Disease> diseases;

}
