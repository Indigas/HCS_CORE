package sk.durovic.collection;


public class Entry <K, V>{

    K key;
    V value;

    public Entry(){}

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

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Entry)
            return key.equals(obj);

        return false;
    }
}
