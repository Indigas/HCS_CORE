package sk.durovic.model;

import org.springframework.data.annotation.Version;

public abstract class BaseEntityAbstractClass<ID> {

    abstract public ID getId();

    @Version
    private Integer version;

    @Override
    public boolean equals(Object obj) {

            if (obj instanceof BaseEntityAbstractClass)
                return ((BaseEntityAbstractClass<?>) obj).getId().equals(this.getId());


        throw new IllegalArgumentException("Object is not instance of BaseEntityAbstractClass");
    }

    Integer getVersion(){
        return version;
    }

    void incrementVersion(){
       this.version++;
    }
}
