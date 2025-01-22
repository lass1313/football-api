package com.nicefootball.football_api.controller;

import com.nicefootball.football_api.dto.TeamDTO;
import com.nicefootball.football_api.entity.Team;
import com.nicefootball.football_api.exception.ResourceNotFoundException;
import com.nicefootball.football_api.service.TeamService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/football")
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);


    @Autowired
    private TeamService teamService;

    /**
     * Récupère une liste paginée et triée d'équipes.
     *
     * @param pageable les informations de pagination et de tri
     * @return une liste paginée de TeamDTO
     */
    @GetMapping("/teams")
    public ResponseEntity<Page<TeamDTO>>  getTeams(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        logger.info("Récupération des équipes avec pagination : {}", pageable);
        try {
            Page<TeamDTO> teams = teamService.getAllTeams(pageable);
            logger.info("Équipes récupérées avec succès");
            return ResponseEntity.ok(teams);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des équipes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    /**
     * Ajoute une nouvelle équipe avec ou sans joueurs associés.
     *
     * @param teamDto les informations de l'équipe à ajouter
     * @return l'équipe ajoutée sous forme de ResponseEntity contenant TeamDTO
     */
    @PostMapping("/teams")
    public ResponseEntity<TeamDTO> addTeam(@Valid @RequestBody TeamDTO teamDto) {
        logger.info("Ajout d'une nouvelle équipe : {}", teamDto.getName());
        try {
            TeamDTO createdTeam = teamService.addTeam(teamDto);
            logger.info("Équipe ajoutée avec succès : {}", createdTeam.getName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTeam);
        }catch (Exception e){
            logger.error("Erreur lors de l'ajout de l'équipe : {}", teamDto.getName(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Récupère une équipe par son identifiant.
     *
     * @param id l'identifiant de l'équipe à récupérer
     * @return l'équipe sous forme de ResponseEntity contenant TeamDTO
     */
    @GetMapping("/teams/{id}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable Long id) {
        logger.info("Récupération de l'équipe avec ID : {}", id);
        try{
            TeamDTO team = teamService.getTeamById(id);
            logger.info("Équipe récupérée avec succès : {}", team.getName());
            return ResponseEntity.ok(team);
        }catch (Exception e){
            logger.error("Erreur lors de la récupération de l'équipe avec ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



}
