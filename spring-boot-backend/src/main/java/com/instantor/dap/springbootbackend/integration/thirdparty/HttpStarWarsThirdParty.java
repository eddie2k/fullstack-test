package com.instantor.dap.springbootbackend.integration.thirdparty;

import com.instantor.dap.springbootbackend.integration.thirdparty.exception.HttpStarWarsThirdPartyException;
import com.instantor.dap.springbootbackend.integration.thirdparty.model.People;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacterImpl;
import com.instantor.dap.springbootbackend.properties.StarWarsApiProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.security.InvalidParameterException;
import java.util.Arrays;

@Component
public class HttpStarWarsThirdParty implements StarWarsThirdParty {

    private final String starWarsCharacterApiEndPoint;

    public HttpStarWarsThirdParty(StarWarsApiProperties starWarsApiProperties) {
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
    }

    @Override
    public int getNumberOfAvailableCharacters() throws HttpStarWarsThirdPartyException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<People> response = restTemplate.exchange(starWarsCharacterApiEndPoint, HttpMethod.GET, entity, People.class);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new HttpStarWarsThirdPartyException("Error: third party returned code " + response.getStatusCode());
            }
            return response.getBody().getCount();
        } catch (RestClientException e) {
            throw new HttpStarWarsThirdPartyException("Error: unexpected error. Details: \"" + e.getLocalizedMessage()
                    + "\"");
        }
    }

    @Override
    public StarsWarsCharacter getStarWarsCharacter(Integer i) {
        return new StarsWarsCharacterImpl("Luke Skywalker");
    }
}
