package sk.durovic.service.impl;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import sk.durovic.helper.Helper;
import sk.durovic.model.Patient;
import sk.durovic.model.Patient_Diagnose;
import sk.durovic.repository.PatientDiagnoseRepository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PatientDiagnoseServiceImplTest {

    @Mock
    private PatientDiagnoseRepository repo;

    @InjectMocks
    private PatientDiagnoseServiceImpl service;

    private Patient_Diagnose patient_diagnose;
    private String idPatient_diagnose;

    @BeforeEach
    private void setUp() throws NoSuchFieldException, IllegalAccessException {
        idPatient_diagnose = "123456test";
        patient_diagnose = new Patient_Diagnose();
        Helper.setIdOfInstance(patient_diagnose, "id", idPatient_diagnose);
    }

    @Test
    void getDiagnosesByPatientId() {
        List<Patient_Diagnose> list = List.of(patient_diagnose);
        String id = idPatient_diagnose;

        Mockito.when(repo.findByPatientId(id)).thenReturn(Optional.of(list));

        List<Patient_Diagnose> getList = service.getDiagnosesByPatientId(id);

        Mockito.verify(repo, Mockito.atMostOnce()).findByPatientId(id);
        assertThat(getList, Matchers.notNullValue());
        assertThat(getList, Matchers.hasSize(1));
        assertThat(getList, Matchers.hasItem(Matchers.hasProperty("id", Matchers.is(id))));
    }


}