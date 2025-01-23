package com.nicefootball.football_api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.nicefootball.football_api.dto.PlayerDTO;
import com.nicefootball.football_api.dto.TeamDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
                .andExpect(jsonPath("$.name").value("Nice FC"))
                .andDo(print());
    }

    @Test
    public void testAddTeamWithPlayers() throws Exception {
        // Création des joueurs
        PlayerDTO player1 = new PlayerDTO();
        player1.setName("Lassana SAKHO");
        player1.setPosition("Attaquant");

        PlayerDTO player2 = new PlayerDTO();
        player2.setName("Clauss");
        player2.setPosition("Défenseur");

        // Création de l'équipe avec les joueurs
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName("Nice FC");
        teamDTO.setAcronym("NFC");
        teamDTO.setBudget(1000000.0);
        teamDTO.setPlayers(Arrays.asList(player1, player2)); // Ajout des joueurs à l'équipe

        mockMvc.perform(post("/api/football/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(teamDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Nice FC"))
                .andExpect(jsonPath("$.players[0].name").value("Lassana SAKHO"))
                .andExpect(jsonPath("$.players[0].position").value("Attaquant"))
                .andExpect(jsonPath("$.players[1].name").value("Clauss"))
                .andExpect(jsonPath("$.players[1].position").value("Défenseur"))
                .andDo(print());
    }

}
