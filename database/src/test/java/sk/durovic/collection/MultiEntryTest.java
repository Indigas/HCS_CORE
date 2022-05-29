package sk.durovic.collection;

import org.junit.jupiter.api.Test;
import sk.durovic.manager.basic.Version;

import static org.junit.jupiter.api.Assertions.*;

class MultiEntryTest {

    @Test
    void put() {
    }

    @Test
    void getKey() {
    }

    @Test
    void getValue() {
    }

    @Test
    void clear() {
    }

    @Test
    void createEntryWithVersion() {
    }

    @Test
    void testEquals() {
        Entry<Version.Status, String> multiEntry = new MultiEntry<>();
        multiEntry.put(Version.Status.LOCK, "TEST");

        Entry<Version.Status, String> multiEntry2 = new MultiEntry<>();
        multiEntry2.put(Version.Status.LOCK, "TEST");

        assertEquals(multiEntry, multiEntry2);
    }

    @Test
    void notEquals(){
        Entry<Version.Status, String> multiEntry = new MultiEntry<>();
        multiEntry.put(Version.Status.LOCK, "TEST");

        Entry<Version.Status, String> multiEntry2 = new MultiEntry<>();
        multiEntry2.put(Version.Status.LOCK, "testing");

        assertNotEquals(multiEntry, multiEntry2);
    }
}