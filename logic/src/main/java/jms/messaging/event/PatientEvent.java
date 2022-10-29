package jms.messaging.event;

import lombok.Data;

import java.io.Serializable;

@Data
public class PatientEvent implements Serializable {

    //public Patient patient;
    private String patient;
}
