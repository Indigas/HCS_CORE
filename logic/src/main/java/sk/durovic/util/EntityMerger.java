package sk.durovic.util;

public interface EntityMerger {

    <T> T merge(T src, T dst);
}
