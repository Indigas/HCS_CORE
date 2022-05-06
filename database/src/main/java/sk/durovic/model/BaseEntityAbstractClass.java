package sk.durovic.model;

import org.springframework.data.annotation.Version;

public abstract class BaseEntityAbstractClass<ID> {

    abstract public ID getId();


    @Override
    public boolean equals(Object obj) {



            if (obj instanceof BaseEntityAbstractClass) {
                if (this.getId() == null || ((BaseEntityAbstractClass) obj).getId() == null)
                    return false;


                return ((BaseEntityAbstractClass<?>) obj).getId().equals(this.getId());
            }


        throw new IllegalArgumentException("Object is not instance of BaseEntityAbstractClass");
    }

}
