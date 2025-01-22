package com.nicefootball.football_api.service;

import com.nicefootball.football_api.dto.PlayerDTO;
import com.nicefootball.football_api.entity.Player;
import com.nicefootball.football_api.exception.ResourceNotFoundException;
import com.nicefootball.football_api.mapper.PlayerMapper;
import com.nicefootball.football_api.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;


    /**
     * Récupère une liste paginée de tous les joueurs.
     *
     * @param pageable les informations de pagination
     * @return une liste paginée de PlayerDTO
     */
    public Page<PlayerDTO> getAllPlayers(Pageable pageable) {
        return playerRepository.findAll(pageable)
                .map(playerMapper::toDto);
    }

    /**
     * Ajoute un nouveau joueur.
     *
     * @param playerDto les informations du joueur à ajouter
     * @return le joueur ajouté sous forme de PlayerDTO
     */
    public PlayerDTO addPlayer(PlayerDTO playerDto) {
        Player player = playerMapper.toEntity(playerDto);
        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toDto(savedPlayer);
    }

    /**
     * Met à jour les informations d'un joueur existant.
     *
     * @param id l'identifiant du joueur à mettre à jour
     * @param playerDTO les nouvelles informations du joueur
     * @return le joueur mis à jour sous forme de PlayerDTO
     * @throws ResourceNotFoundException si le joueur n'est pas trouvé
     */
    public PlayerDTO updatePlayer(Long id, PlayerDTO playerDTO) {

        Player player = playerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Joueur non trouvé"));
        player.setName(playerDTO.getName());
        player.setPosition(playerDTO.getPosition());
        Player updatedPlayer = playerRepository.save(player);
        return playerMapper.toDto(updatedPlayer);
    }

    /**
     * Supprime un joueur par son identifiant.
     *
     * @param id l'identifiant du joueur à supprimer
     */
    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }














}
