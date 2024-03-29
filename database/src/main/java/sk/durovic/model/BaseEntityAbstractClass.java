package sk.durovic.model;


import sk.durovic.model.parent.Parent;

public abstract class BaseEntityAbstractClass<ID> implements Parent {

    abstract public ID getId();

    abstract public void setId(ID id);

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
