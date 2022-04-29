package sk.durovic.model;

public abstract class BaseEntityAbstractClass<ID> {

    abstract public ID getId();

    @Override
    public boolean equals(Object obj) {

            if (obj instanceof BaseEntityAbstractClass)
                return ((BaseEntityAbstractClass<?>) obj).getId().equals(this.getId());


        throw new IllegalArgumentException("Object is not instance of BaseEntityAbstractClass");
    }
}
