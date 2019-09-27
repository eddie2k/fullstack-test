package com.instantor.dap.springbootbackend.integration.thirdparty;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacterImpl;
import org.springframework.stereotype.Component;

@Component
public class HttpStarWarsThirdParty implements StarWarsThirdParty {

    @Override
    public int getNumberOfAvailableCharacters() {
        return 5;
    }

    @Override
    public StarsWarsCharacter getStarWarsCharacter(Integer i) {
        return new StarsWarsCharacterImpl("Luke Skywalker");
    }
}
