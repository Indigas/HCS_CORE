package sk.durovic.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity extends BaseEntityAbstractClass<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BaseEntity)
            return ((BaseEntity)obj).getId().equals(this.id);

        throw new IllegalArgumentException("Object is not instance of BaseEntity");
    }
}
