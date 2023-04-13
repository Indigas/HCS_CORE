package sk.durovic.util;

public interface EntityMerger {

    <T> T merge(Object src, Object dst);
}
