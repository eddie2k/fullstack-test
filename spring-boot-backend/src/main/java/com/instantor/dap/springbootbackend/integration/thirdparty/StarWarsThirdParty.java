package com.instantor.dap.springbootbackend.integration.thirdparty;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;

/**
 * The actual low-level integration to the API
 */
public interface StarWarsThirdParty {
    int getNumberOfAvailableCharacters();

    StarsWarsCharacter getStarWarsCharacter(Integer i);
}
