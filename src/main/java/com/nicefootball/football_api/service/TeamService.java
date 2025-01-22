package com.nicefootball.football_api.service;

import com.nicefootball.football_api.dto.TeamDTO;
import com.nicefootball.football_api.entity.Team;
import com.nicefootball.football_api.exception.ResourceNotFoundException;
import com.nicefootball.football_api.mapper.TeamMapper;
import com.nicefootball.football_api.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeamService {


    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamMapper teamMapper;


    /**
     * Récupère une liste paginée et triée de toutes les équipes.
     *
     * @param pageable les informations de pagination et de tri
     * @return une liste paginée de TeamDTO
     */
    public Page<TeamDTO> getAllTeams(Pageable pageable) {
        return teamRepository.findAll(pageable)
                .map(teamMapper::toDto);
    }
    /**
     * Ajoute une nouvelle équipe avec ou sans joueurs associés.
     *
     * @param teamDto les informations de l'équipe à ajouter
     * @return l'équipe ajoutée sous forme de TeamDTO
     */
    public TeamDTO addTeam(TeamDTO teamDto) {
        Team team = teamMapper.toEntity(teamDto);
        Team savedTeam = teamRepository.save(team);
        return teamMapper.toDto(savedTeam);
    }

    /**
     * Récupère une équipe par son identifiant.
     *
     * @param id l'identifiant de l'équipe à récupérer
     * @return l'équipe sous forme de TeamDTO
     * @throws ResourceNotFoundException si l'équipe n'est pas trouvée
     */
    public TeamDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipe non trouvé"));
        return teamMapper.toDto(team);
    }


}
