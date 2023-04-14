package sk.durovic.util;

public interface EntityMerger {

    <T,R> R merge(T src, R dst);
}
