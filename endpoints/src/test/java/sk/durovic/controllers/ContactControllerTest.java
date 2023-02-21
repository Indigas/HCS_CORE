package sk.durovic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sk.durovic.configuration.EntityServiceManagerConfiguration;
import sk.durovic.manager.ServiceContainer;
import sk.durovic.manager.service.EntityServiceManager;
import sk.durovic.model.Contact;
import sk.durovic.model.access.ContactEntity;
import sk.durovic.service.ContactEntityService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ContactController.class)
@Import(EntityServiceManagerConfiguration.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactEntityService entityService;

    private Contact contact;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        ContactEntity entity = new ContactEntity();
        entity.setFullName("Spring Test");
        contact = entity.createContact();
    }

    @Test
    void getEntities() throws Exception {
        Mockito.when(entityService.load(Mockito.any())).thenReturn(Optional.ofNullable(contact));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/contact/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
        Mockito.verify(entityService, Mockito.atMostOnce()).load(Mockito.any());
    }

    @Test
    void createPatients() {
    }

    @Test
    void updatePatient() {
    }

    @Test
    void updatePatients() {
    }

    @Test
    void deletePatient() {
    }

    @Test
    void deletePatients() {
    }
}