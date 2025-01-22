package com.nicefootball.football_api.controller;


import com.nicefootball.football_api.dto.PlayerDTO;
import com.nicefootball.football_api.service.PlayerService;
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
@RequestMapping("/api/football/")
public class PlayerController {

    private static final Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerService playerService;

    /**
     * Récupère une liste paginée de joueurs.
     *
     * @param pageable les informations de pagination et de tri
     * @return une liste paginée de PlayerDTO
     */
    @GetMapping("/players")
    public  ResponseEntity<Page<PlayerDTO>>  getPlayers(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        logger.info("Récupération des joueurs avec pagination : {}", pageable);
        try {
            Page<PlayerDTO> players = playerService.getAllPlayers(pageable);
            logger.info("Joueurs récupérés avec succès");
            return ResponseEntity.ok(players);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des joueurs", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Ajoute un nouveau joueur.
     *
     * @param playerDto les informations du joueur à ajouter
     * @return le joueur ajouté sous forme de ResponseEntity contenant PlayerDTO
     */
    @PostMapping("/players")
    public ResponseEntity<PlayerDTO> addPlayer(@Valid @RequestBody PlayerDTO playerDto) {
        logger.info("Ajout d'un nouveau joueur : {}", playerDto.getName());
        try {
            PlayerDTO createdPlayer = playerService.addPlayer(playerDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
        }catch (Exception e){
            logger.error("Erreur lors de l'ajout du joueur : {}", playerDto.getName(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Met à jour les informations d'un joueur existant.
     *
     * @param id l'identifiant du joueur à mettre à jour
     * @param playerDto les nouvelles informations du joueur
     * @return le joueur mis à jour sous forme de ResponseEntity contenant PlayerDTO
     */
    @PutMapping("/players/{id}")
    public ResponseEntity<PlayerDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerDTO playerDto) {
        logger.info("Mise à jour du joueur avec ID : {}", id);
        try {
            PlayerDTO updatedPlayer = playerService.updatePlayer(id, playerDto);
            logger.info("Joueur mis à jour avec succès : {}", updatedPlayer.getName());
            return ResponseEntity.ok(updatedPlayer);
        }catch (Exception e){
            logger.error("Erreur lors de la mise à jour du joueur avec ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Supprime un joueur par son identifiant.
     *
     * @param id l'identifiant du joueur à supprimer
     * @return une ResponseEntity sans contenu
     */
    @DeleteMapping("/players/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        logger.info("Suppression du joueur avec ID : {}", id);
        try{
            playerService.deletePlayer(id);
            logger.info("Joueur supprimé avec succès : {}", id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            logger.error("Erreur lors de la suppression du joueur avec ID : {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }







}
