package com.adrian.assignment.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AdditionObjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAddValuesValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/addition")
                        .param("addable1", "2")
                        .param("addable2", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.addable1").value(2))
                .andExpect(jsonPath("$.addable2").value(3))
                .andExpect(jsonPath("$.sum").value(5));
    }

    @Test
    public void testAddValuesInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/addition")
                        .param("addable1", "abc")
                        .param("addable2", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFindPreviousAdditionsValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/history")
                        .param("value", "5")
                        .param("ascending", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testFindPreviousAdditionsInvalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/history")
                        .param("value", "abc")
                        .param("ascending", "true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
