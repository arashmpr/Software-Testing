package controllers;

import application.BalootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import java.util.HashMap;
import java.util.Map;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = BalootApplication.class)
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class CommoditiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetCommodities() throws Exception {
        mockMvc.perform(get("/commodities"))


                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].name").value("iPhone"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].name").value("Galaxy"));
    }

    @Test
    public void testGetCommodity_Success() throws Exception {
        mockMvc.perform(get("/commodities/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("iPhone"));
    }

    @Test
    public void testGetCommodity_NotFound() throws Exception {
        mockMvc.perform(get("/commodities/{id}", "3"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRateCommodity_Success() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("username", "amin");
        input.put("rate", "3");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(input);

        mockMvc.perform(post("/commodities/{id}/rate", "1")
                        .headers(httpHeaders)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("rate added successfully!"));
    }

    @Test
    public void testRateCommodity_NotFound() throws Exception {
        mockMvc.perform(post("/commodities/{id}/rate", "3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rate\": 5, \"username\": \"user1\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testRateCommodity_BadRequest() throws Exception {
        mockMvc.perform(post("/commodities/{id}/rate", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rate\": \"invalid\", \"username\": \"user1\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testaddCommodityComment_Success() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "amin");
        requestBody.put("comment", "Hello");

        mockMvc.perform(post("/commodities/{id}/comment", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("comment added successfully!"));
    }
    @Test
    public void testAddCommodityComment_UserNotFound() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("username", "nonexistentUser");
        requestBody.put("comment", "Hello");

        mockMvc.perform(post("/commodities/{id}/comment", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestBody)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User does not exist."));
    }
    @Test
    public void testGetCommodityComment_Success() throws Exception {

        mockMvc.perform(get("/commodities/{id}/comment", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk());
        }


    @Test
    public void testGetCommodityComment_CommentsNotFound() throws Exception {

        mockMvc.perform(get("/commodities/{id}/comment", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void searchCommoditiesByName_Success() throws Exception {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("searchOption", "name");
        requestBody.put("searchValue", "iPhone");

        mockMvc.perform(post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchOption\": \"name\", \"searchValue\": \"iPhone\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("iPhone"));
    }

    @Test
    public void searchCommoditiesByCategory_Success() throws Exception {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("searchOption", "category");
        requestBody.put("searchValue", "tech");

        mockMvc.perform(post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchOption\": \"category\", \"searchValue\": \"tech\"}"))
                .andDo(print()) // Print the response content for debugging
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].categories[1]").value("tech")) // Check the first category of the first result
                .andExpect(jsonPath("$[1].categories[1]").value("tech")); // Check the first category of the second result
    }
    @Test
    public void searchCommoditiesByProvider_Success() throws Exception {

        mockMvc.perform(post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchOption\": \"provider\", \"searchValue\": \"1\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].providerId").value("1"));
    }
    @Test
    public void searchCommodities_InvalidSearchOption() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "invalidOption");
        input.put("searchValue", "someValue");

        mockMvc.perform(post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchOption\": \"invalidOption\", \"searchValue\": \"someValue\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void searchCommodities_EmptySearchValue() throws Exception {
        Map<String, String> input = new HashMap<>();
        input.put("searchOption", "name");
        input.put("searchValue", "");

        mockMvc.perform(post("/commodities/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"searchOption\": \"name\", \"searchValue\": \"\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void getSuggestedCommodities_Success() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/suggested", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void getSuggestedCommodities_NotFound() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/commodities/{id}/suggested", "232")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
}