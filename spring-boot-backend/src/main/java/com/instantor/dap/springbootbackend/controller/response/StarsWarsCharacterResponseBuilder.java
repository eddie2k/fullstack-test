package com.instantor.dap.springbootbackend.controller.response;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;

public final class StarsWarsCharacterResponseBuilder {

    private Boolean integrationStatus;
    private StarsWarsCharacter starsWarsCharacter;

    public StarsWarsCharacterResponseBuilder setIntegrationStatus(boolean integrationStatus) {
        this.integrationStatus = integrationStatus;
        return this;
    }

    public StarsWarsCharacterResponseBuilder setStarsWarsCharacter(StarsWarsCharacter starsWarsCharacter) {
        this.starsWarsCharacter = starsWarsCharacter;
        return this;
    }

    public StarsWarsCharacterResponse build() {
        if (integrationStatus == null) {
            throw new IllegalStateException("integrationStatus is missing");
        }
        return new StarsWarsCharacterResponse(integrationStatus, starsWarsCharacter);
    }
}