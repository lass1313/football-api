package com.nicefootball.football_api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicefootball.football_api.dto.TeamDTO;
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
public class TeamControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTeams() throws Exception {
        mockMvc.perform(get("/api/football/teams")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());
    }

    @Test
    public void testAddTeam() throws Exception {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName("Nice FC");
        teamDTO.setAcronym("NFC");
        teamDTO.setBudget(1000000.0);

        mockMvc.perform(post("/api/football/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(teamDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Nice FC"));
    }

}
