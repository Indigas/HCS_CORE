package sk.durovic.collection;

/**
 * MultiEntry is used, if object has to be equals, only when key and value is the same.
 * @param <K>
 * @param <V>
 */
public class MultiEntry<K, V> extends Entry<K, V>{

    public MultiEntry(){}

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
            return this.value.equals(obj);

        return false;
    }
}
