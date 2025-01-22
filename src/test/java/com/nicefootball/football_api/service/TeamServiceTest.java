package com.nicefootball.football_api.service;

import com.nicefootball.football_api.dto.TeamDTO;
import com.nicefootball.football_api.entity.Team;
import com.nicefootball.football_api.mapper.TeamMapper;
import com.nicefootball.football_api.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;
    @Mock
    private TeamMapper teamMapper;
    @InjectMocks
    private TeamService teamService;

    @Test
    public void testAddTeam() {
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName("Nice FC");
        teamDTO.setAcronym("NFC");
        teamDTO.setBudget(1000000.0);

        Team team = new Team();
        team.setName("Nice FC");
        team.setAcronym("NFC");
        team.setBudget(1000000.0);

        when(teamMapper.toEntity(teamDTO)).thenReturn(team);
        when(teamRepository.save(team)).thenReturn(team);
        when(teamMapper.toDto(team)).thenReturn(teamDTO);

        TeamDTO result = teamService.addTeam(teamDTO);
        assertEquals("Nice FC", result.getName());
    }
}
