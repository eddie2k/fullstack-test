package com.instantor.dap.springbootbackend.integration;

import com.instantor.dap.springbootbackend.integration.exception.StarWarsIntegrationException;
import com.instantor.dap.springbootbackend.integration.thirdparty.StarWarsThirdParty;
import com.instantor.dap.springbootbackend.integration.thirdparty.exception.StarWarsThirdPartyCommunicationException;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class HttpStarWarsIntegration implements StarWarsIntegration {

    @Autowired
    private StarWarsThirdParty starWarsThirdParty;

    @Autowired
    private RandomGenerator randomGenerator;

    @Override
    public StarsWarsCharacter getStarWarsCharacter() {
        try {
            int max = starWarsThirdParty.getNumberOfAvailableCharacters();
            int i = randomGenerator.getInt(max);
            return starWarsThirdParty.getStarWarsCharacter(i);
        } catch (StarWarsThirdPartyCommunicationException e) {
            throw new StarWarsIntegrationException(e);
        }
    }
}

@Component
class RandomGenerator {
    private final Random random = new Random();

    /**
     * Generates a random number in the closed range [1..max]
     *
     * @param max maximum value (inclusive)
     * @return
     */
    public int getInt(int max) {
        return random.nextInt(max) + 1;
    }
}