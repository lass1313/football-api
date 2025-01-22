package com.nicefootball.football_api.mapper;

import com.nicefootball.football_api.dto.PlayerDTO;
import com.nicefootball.football_api.entity.Player;
import org.springframework.stereotype.Component;

/**
 * Mapper pour convertir entre les entités Player et les DTO PlayerDTO.
 */
@Component
public class PlayerMapper {

    /**
     * Convertit une entité Player en PlayerDTO.
     *
     * @param player l'entité Player à convertir
     * @return le PlayerDTO correspondant
     */
    public PlayerDTO toDto(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setPosition(player.getPosition());
        return dto;
    }

    /**
     * Convertit un PlayerDTO en entité Player.
     *
     * @param dto le PlayerDTO à convertir
     * @return l'entité Player correspondante
     */
    public Player toEntity(PlayerDTO dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setPosition(dto.getPosition());
        return player;
    }
}
