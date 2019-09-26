package com.instantor.dap.springbootbackend.controller.response;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;

/**
 * A DTO that holds the call to the server, including whether the information is available, and the character
 * information itself
 */
public final class StarsWarsCharacterResponse {

    private final boolean integrationStatus;
    private final StarsWarsCharacter starsWarsCharacter;

    StarsWarsCharacterResponse(boolean integrationStatus, StarsWarsCharacter starsWarsCharacter) {
        this.integrationStatus = integrationStatus;
        this.starsWarsCharacter = starsWarsCharacter;
    }

    public StarsWarsCharacter getStarWarsCharacter() {
        return starsWarsCharacter;
    }

    public boolean getIntegrationOk() {
        return integrationStatus;
    }
}
