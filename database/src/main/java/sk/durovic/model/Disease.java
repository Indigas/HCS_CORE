package sk.durovic.model;

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
public class Disease {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private String description;
}
