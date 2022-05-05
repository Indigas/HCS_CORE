package sk.durovic.collection;

public class MultiEntry<K, V> extends Entry<K, V>{

    public MultiEntry(){}

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
            return this.value.equals(obj);

        return false;
    }
}
