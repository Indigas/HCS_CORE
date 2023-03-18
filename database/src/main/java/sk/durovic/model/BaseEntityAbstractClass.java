package sk.durovic.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import sk.durovic.manager.basic.Version;

import java.io.Serializable;

public abstract class BaseEntityAbstractClass<ID> {

    @JsonIgnore
    private Version version = new Version();

    abstract public ID getId();

    public Version getVersion(){
        return this.version;
    }


    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;

        if (obj instanceof BaseEntityAbstractClass) {
            if (this.getId() == null || ((BaseEntityAbstractClass<?>) obj).getId() == null)
                return false;


            return ((BaseEntityAbstractClass<ID>) obj).getId().equals(this.getId());
        }


        //throw new IllegalArgumentException("Object is not instance of BaseEntityAbstractClass");
        return false;
    }

}
