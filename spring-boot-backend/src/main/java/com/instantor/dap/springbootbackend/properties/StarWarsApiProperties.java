package com.instantor.dap.springbootbackend.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "starwarsapi")
public class StarWarsApiProperties {

    private String endpoint;
    private String characterspath;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getCharacterspath() {
        return characterspath;
    }

    public void setCharacterspath(String characterspath) {
        this.characterspath = characterspath;
    }
}