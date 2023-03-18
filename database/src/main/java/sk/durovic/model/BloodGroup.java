package sk.durovic.model;

import java.io.Serializable;

public enum BloodGroup {
    AA("AA");

    private BloodGroup(String bloodGroup) {
        this.value = bloodGroup;
    }

    private String value;

    private String getValue(){
        return value;
    }
}
