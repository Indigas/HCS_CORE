package sk.durovic.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import sk.durovic.dto.PatientDto;
import sk.durovic.helper.EntityMapperHelper;
import sk.durovic.mapper.EntityConverter;
import sk.durovic.mapper.EntityMapper;
import sk.durovic.model.BloodGroup;
import sk.durovic.model.Patient;
import sk.durovic.repository.PatientDiagnoseRepository;
import sk.durovic.result.EntityResult;
import sk.durovic.result.Result;
import sk.durovic.service.PatientEntityService;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @MockBean
    public PatientEntityService service;

    @Autowired
    private MockMvc mockMvc;

    private Patient patient;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        patient = createTestPatient();
    }


    @Test
    void getEntities() throws Exception {
        Mockito.when(service.loadAll(Mockito.anyCollection())).thenReturn(List.of(patient));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/patient/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Map<String, String> map = createMapFromResult(result);

        MatcherAssert.assertThat(map.get("id"), Matchers.is(patient.getId()));

    }

    @Test
    void createEntities() throws Exception {
        Mockito.when(service.saveAll(Mockito.anyCollection())).thenReturn(List.of(patient));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post("/patient")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(convertToDto(patient)))))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        Map<String, String> map = createMapFromResult(result);

        MatcherAssert.assertThat(map.get("id"), Matchers.is(patient.getId()));
    }

    @Test
    void updateEntity() {
    }

    @Test
    void updateEntities() {
    }

    @Test
    void deleteEntityWithOutId() throws Exception {
        patient.setId(null);
        Mockito.verify(service, Mockito.atMostOnce()).deleteAll(Mockito.anyCollection());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/patient")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(convertToDto(patient)))))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void deleteEntities() {

    }

    @Test
    void handleException() {
    }

    @Test
    void createEntity() {
    }

    private Patient createTestPatient(){
        Patient patientTest = new Patient();
        patientTest.setId("123");
        patientTest.setFirstName("Marek");
        patientTest.setLastName("Durovic");
        patientTest.setBloodGroup(BloodGroup.AA);
        patientTest.setEmail("marek@mm.sk");

        return patientTest;
    }

    private Map<String, String> createMapFromResult(MvcResult result) throws UnsupportedEncodingException, JsonProcessingException {
        Collection<Map<String, String>> mapCollection = getMapCollection(result);

        return mapCollection.iterator().hasNext() ? mapCollection.iterator().next() : new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    private Collection<Map<String, String>> getMapCollection(MvcResult result) throws UnsupportedEncodingException, JsonProcessingException {
        Result entityResult = objectMapper.readValue(result.getResponse().getContentAsString(), EntityResult.class);
        return (Collection<Map<String, String>>) entityResult.getEntities();
    }

    private PatientDto convertToDto(Patient patient){
        EntityConverter<PatientDto, Patient> converter = EntityMapperHelper.getConverter(Patient.class);
        return converter.convert2Dto(patient);
    }
}