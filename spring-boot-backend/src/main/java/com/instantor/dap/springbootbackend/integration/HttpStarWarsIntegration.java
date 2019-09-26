package com.instantor.dap.springbootbackend.integration;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacterImpl;
import org.springframework.stereotype.Service;

@Service
public class HttpStarWarsIntegration implements StarWarsIntegration {

    @Override
    public StarsWarsCharacter getStarWarsCharacter() {
        return new StarsWarsCharacterImpl("Luke Skywalker");
    }
}