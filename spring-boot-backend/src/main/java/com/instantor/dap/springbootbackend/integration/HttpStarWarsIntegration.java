package com.instantor.dap.springbootbackend.integration;

import com.instantor.dap.springbootbackend.integration.thirdparty.StarWarsThirdParty;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.properties.StarWarsApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.Random;

@Service
public class HttpStarWarsIntegration implements StarWarsIntegration {

    private final StarWarsThirdParty starWarsThirdParty;
    private final RandomGenerator randomGenerator;
    private final String starWarsCharacterApiEndPoint;

    @Autowired
    public HttpStarWarsIntegration(StarWarsThirdParty starWarsThirdParty, StarWarsApiProperties starWarsApiProperties,
                                   RandomGenerator randomGenerator) {
        this.starWarsThirdParty = starWarsThirdParty;
        if (starWarsApiProperties == null) {
            throw new InvalidParameterException();
        }
        if (starWarsApiProperties.getEndpoint() == null) {
            throw new InvalidParameterException();
        }
        if (starWarsApiProperties.getCharacterspath() == null) {
            throw new InvalidParameterException();
        }
        starWarsCharacterApiEndPoint = starWarsApiProperties.getEndpoint() + "/" + starWarsApiProperties.getCharacterspath();
        this.randomGenerator = randomGenerator;
    }

    @Override
    public StarsWarsCharacter getStarWarsCharacter() {
        int max = starWarsThirdParty.getNumberOfAvailableCharacters();
        int i = randomGenerator.getValidCharacterNumber(max);
        return starWarsThirdParty.getStarWarsCharacter(i);
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
    public int getValidCharacterNumber(int max) {
        return random.nextInt(max) + 1;
    }
}