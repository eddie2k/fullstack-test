package com.instantor.dap.springbootbackend.controller;

import com.instantor.dap.springbootbackend.integration.StarWarsIntegration;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/sw")
public class StarWarsCharacterController {

    private final StarWarsIntegration starWarsIntegration;

    public StarWarsCharacterController(StarWarsIntegration starWarsIntegration) {
        this.starWarsIntegration = starWarsIntegration;
    }

    /**
     * @return character from Star Wars
     */
    @GetMapping("/character")
    public StarsWarsCharacter getCharacter() {
        return starWarsIntegration.getStarWarsCharacter();
    }
}