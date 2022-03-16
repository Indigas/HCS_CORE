package sk.durovic.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Contact{

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String fullName;

    @Column
    private String telephone;

    @Column
    private String notes;
}
