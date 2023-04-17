package sk.durovic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity extends BaseEntityAbstractClass<Long> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = -1L;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
//        if(obj == null)
//            return false;
//
//        if(obj instanceof BaseEntity)
//            return ((BaseEntity)obj).getId().equals(this.id);

        //throw new IllegalArgumentException("Object is not instance of BaseEntity");

        return super.equals(obj) && !getId().equals(-1L);

    }
}
