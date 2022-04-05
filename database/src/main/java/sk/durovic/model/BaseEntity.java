package sk.durovic.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity implements BaseEntityInterface<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }
}
