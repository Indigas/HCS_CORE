package sk.durovic.model;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity extends BaseEntityAbstractClass<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if(obj instanceof BaseEntity)
            return ((BaseEntity)obj).getId().equals(this.id);

        //throw new IllegalArgumentException("Object is not instance of BaseEntity");
        return false;
    }
}
