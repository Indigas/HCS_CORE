package sk.durovic.collection;

import org.junit.jupiter.api.Test;
import sk.durovic.manager.basic.Version;
import sk.durovic.model.access.PatientEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EntryTest {

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
        Entry<Version.Status, PatientEntity> entry = new Entry<>();
        entry.put(Version.Status.LOCK, new PatientEntity());

        Entry<Version.Status, PatientEntity> entry2 = new Entry<>();
        entry2.put(Version.Status.LOCK, new PatientEntity());

        assertEquals(entry, entry2);
    }

    @Test
    void notEquals(){
        Entry<Version.Status, PatientEntity> entry = new Entry<>();
        entry.put(Version.Status.LOCK, new PatientEntity());

        Entry<Version.Status, PatientEntity> entry2 = new Entry<>();
        entry2.put(Version.Status.TO_SAVE, new PatientEntity());

        assertNotEquals(entry, entry2);
    }
}