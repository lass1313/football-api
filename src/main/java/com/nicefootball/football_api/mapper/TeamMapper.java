package com.nicefootball.football_api.mapper;

import com.nicefootball.football_api.dto.TeamDTO;
import com.nicefootball.football_api.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre les entités Team et les DTO TeamDTO.
 */
@Component
public class TeamMapper {

    @Autowired
    private PlayerMapper playerMapper;

    /**
     * Convertit une entité Team en TeamDTO.
     *
     * @param team l'entité Team à convertir
     * @return le TeamDTO correspondant
     */
    public TeamDTO toDto(Team team) {
        TeamDTO dto = new TeamDTO();
        dto.setId(team.getId());
        dto.setName(team.getName());
        dto.setAcronym(team.getAcronym());
        dto.setBudget(team.getBudget());
        dto.setPlayers(team.getPlayers()
                .stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList()));
        return dto;
    }

    /**
     * Convertit un TeamDTO en entité Team.
     *
     * @param dto le TeamDTO à convertir
     * @return l'entité Team correspondante
     */
    public Team toEntity(TeamDTO dto) {
        Team team = new Team();
        team.setId(dto.getId());
        team.setName(dto.getName());
        team.setAcronym(dto.getAcronym());
        team.setBudget(dto.getBudget());
        if (dto.getPlayers() != null) {
            team.setPlayers(dto.getPlayers()
                    .stream()
                    .map(playerMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return team;
    }
}
