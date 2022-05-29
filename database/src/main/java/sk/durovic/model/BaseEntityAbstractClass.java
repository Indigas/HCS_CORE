package sk.durovic.model;


import sk.durovic.manager.basic.Version;

public abstract class BaseEntityAbstractClass<ID> {

    private Version version = new Version();

    abstract public ID getId();

    public Version getVersion(){
        return this.version;
    }


    @Override
    public boolean equals(Object obj) {

            if (obj instanceof BaseEntityAbstractClass) {
                if (this.getId() == null || ((BaseEntityAbstractClass<?>) obj).getId() == null)
                    return false;


                return ((BaseEntityAbstractClass<?>) obj).getId().equals(this.getId());
            }


        throw new IllegalArgumentException("Object is not instance of BaseEntityAbstractClass");
    }

}
