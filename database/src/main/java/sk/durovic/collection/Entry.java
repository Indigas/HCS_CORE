package sk.durovic.collection;


import sk.durovic.manager.basic.Version;
import sk.durovic.model.BaseEntityAbstractClass;

import java.util.Objects;

/**
 * used for entity container
 * @param <K>
 * @param <V>
 */
public class Entry <K, V>{

    K key;
    V value;

    public Entry(){}

    public Entry(K key, V value){
        this.key = key;
        this.value = value;
    }

    public void put(K key, V value){
        this.key = key;
        this.value = value;
    }

    public K getKey(){
        return this.key;
    }

    public V getValue(){
        return this.value;
    }

    public void clear(){
        this.key = null;
        this.value = null;
    }

    public static <T extends BaseEntityAbstractClass<?>> Entry<T , Version> createEntryWithVersion(T entity, Version version) throws IllegalAccessException {
        //Version newVersion = new Version();
        //EntityMapper.mapToExistingObject(version, newVersion);
        //newVersion.incrementVersion();

        version.incrementVersion();

        return new Entry<>(entity, version);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Entry)
            return key.equals(((Entry<?, ?>)obj).getKey());

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }
}
