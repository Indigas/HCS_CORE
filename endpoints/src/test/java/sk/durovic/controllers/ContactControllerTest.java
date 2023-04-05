package sk.durovic.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sk.durovic.configuration.EntityServiceManagerConfiguration;
import sk.durovic.dto.ContactDto;
import sk.durovic.manager.factory.EntityManagerCreator;

@WebMvcTest(ContactController.class)
@Import(EntityServiceManagerConfiguration.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManagerCreator creator;

    private ContactDto contact;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
//        ContactEntity entity = new ContactEntity();
//        entity.setFullName("Spring Test");
        contact = new ContactDto();
        contact.setFullName("Spring Test");
    }

    @Test
    @Disabled
    void getEntities() throws Exception {
//        Mockito.when(manager.load(Mockito.any())).thenReturn(Optional.ofNullable(contact));

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/contact/5")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
//        Mockito.verify(entityService, Mockito.atMostOnce()).load(Mockito.any());
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