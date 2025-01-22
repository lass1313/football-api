package com.nicefootball.football_api.service;

import com.nicefootball.football_api.dto.PlayerDTO;
import com.nicefootball.football_api.entity.Player;
import com.nicefootball.football_api.mapper.PlayerMapper;
import com.nicefootball.football_api.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class PlayerServiceTest {
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private PlayerMapper playerMapper;
    @InjectMocks
    private PlayerService playerService;

    @Test
    public void testAddPlayer() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("Lassana SAKHO");
        playerDTO.setPosition("Attanquant");

        Player player = new Player();
        player.setName("Lassana SAKHO");
        player.setPosition("Attanquant");

        when(playerMapper.toEntity(playerDTO)).thenReturn(player);
        when(playerRepository.save(player)).thenReturn(player);
        when(playerMapper.toDto(player)).thenReturn(playerDTO);

        PlayerDTO result = playerService.addPlayer(playerDTO);
        assertEquals("Lassana SAKHO", result.getName());
    }
}
