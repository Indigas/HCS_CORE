package sk.durovic.actions;

public interface RequestAction<T, ID> {
    void get();
    void post();
    void put();
    void delete();
}
