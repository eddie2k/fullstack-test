package com.instantor.dap.springbootbackend.integration.thirdparty;

import com.instantor.dap.springbootbackend.integration.thirdparty.exception.StarWarsThirdPartyCommunicationException;
import com.instantor.dap.springbootbackend.integration.thirdparty.model.People;
import com.instantor.dap.springbootbackend.integration.thirdparty.model.Result;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacterImpl;
import com.instantor.dap.springbootbackend.properties.StarWarsApiProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.function.Function;

import static org.springframework.http.HttpMethod.GET;

@Component
public class HttpStarWarsThirdParty implements StarWarsThirdParty {

    private static final HttpEntity<String> HTTP_ENTITY = getHttpEntity();

    private final String apiEntryEndpoint;

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
        apiEntryEndpoint = starWarsApiProperties.getEndpoint() + "/" + starWarsApiProperties.getCharacterspath();
    }

    @Override
    public int getNumberOfAvailableCharacters() throws StarWarsThirdPartyCommunicationException {
        Function<RestTemplate, ResponseEntity<People>> call = restTemplate -> restTemplate.exchange(apiEntryEndpoint, GET, HTTP_ENTITY, People.class);
        Function<ResponseEntity<People>, Integer> extractor = response -> {
            if (response == null || response.getBody() == null || response.getBody().getCount() == null) {
                throw new StarWarsThirdPartyCommunicationException("Error: can't fetch the number of characters");
            }
            return response.getBody().getCount();
        };
        return fetchAndExtract(call, extractor);
    }

    @Override
    public StarsWarsCharacter getStarWarsCharacter(int i) {
        Function<RestTemplate, ResponseEntity<Result>> call = restTemplate -> restTemplate.exchange(apiEntryEndpoint + "/" + i, GET, HTTP_ENTITY, Result.class);
        Function<ResponseEntity<Result>, String> extractor = response -> {
            if (response == null || response.getBody() == null || response.getBody().getName() == null) {
                throw new StarWarsThirdPartyCommunicationException("Error: returned character has no name");
            }
            return response.getBody().getName();
        };
        String name = fetchAndExtract(call, extractor);
        return new StarsWarsCharacterImpl(name);
    }

    private static <P, Q> Q fetchAndExtract(Function<RestTemplate, ResponseEntity<P>> call, Function<ResponseEntity<P>, Q> extractor) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<P> response = call.apply(restTemplate);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new StarWarsThirdPartyCommunicationException("Error: third party returned code " + response.getStatusCode());
            }
            return extractor.apply(response);
        } catch (RestClientException e) {
            throw new StarWarsThirdPartyCommunicationException("Error: unexpected error. Details: \"" + e.getLocalizedMessage() + "\"");
        }
    }

    /**
     * Gets the {@link HttpEntity} with headers set to emulate Mozilla 5.0
     */
    private static HttpEntity<String> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        return new HttpEntity<>("parameters", headers);
    }
}
