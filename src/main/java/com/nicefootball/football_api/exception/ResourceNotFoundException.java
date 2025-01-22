package com.nicefootball.football_api.exception;

/**
 * Exception levée lorsqu'une ressource n'est pas trouvée.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * Construit une nouvelle exception avec le message spécifié.
     *
     * @param message le message détaillant la cause de l'exception
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}