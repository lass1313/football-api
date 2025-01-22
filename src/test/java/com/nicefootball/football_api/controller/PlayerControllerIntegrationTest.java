package com.nicefootball.football_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicefootball.football_api.dto.PlayerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PlayerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetPlayers() throws Exception {
        mockMvc.perform(get("/api/football/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void testAddPlayer() throws Exception {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("Lassana SAKHO");
        playerDTO.setPosition("Attaquant");

        mockMvc.perform(post("/api/football/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(playerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Lassana SAKHO"))
                .andExpect(jsonPath("$.position").value("Attaquant"));
    }

}
