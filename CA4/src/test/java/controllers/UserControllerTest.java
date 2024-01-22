package controllers;

import application.BalootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(classes = BalootApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUser_Success() throws Exception {
        mockMvc.perform(get("/users/{id}", "ali"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("ali"))
                .andExpect(jsonPath("$.password").value("123"))
                .andExpect(jsonPath("$.email").value("ali@gmail.com"))
                .andExpect(jsonPath("$.birthDate").value("2000-01-01"))
                .andExpect(jsonPath("$.address").value("Tehran, Iran"))
                .andExpect(jsonPath("$.credit").value(1000.0));
    }

    @Test
    public void testGetUser_NotFound() throws Exception {
        mockMvc.perform(get("/users/{id}", "hossein"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddCredit_Success() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "500.0");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", "ali")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("credit added successfully!"));
    }

    @Test
    public void testAddCredit_InvalidCreditRange() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "-100.0");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", "ali")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Credit value must be a positive float"));
    }

    @Test
    public void testAddCredit_NotExistentUser() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "100.0");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", "nonexistentUserId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string("User does not exist."));
    }

    @Test
    public void testAddCredit_InvalidCreditFormat() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("credit", "invalidCredit");

        mockMvc.perform(MockMvcRequestBuilders.post("/users/{id}/credit", "ali")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Please enter a valid number for the credit amount."));
    }
}
