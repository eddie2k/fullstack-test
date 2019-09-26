package com.instantor.dap.springbootbackend.controller;

import com.instantor.dap.springbootbackend.integration.StarWarsIntegration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StarWarsCharacterControllerTest {

    @InjectMocks
    private StarWarsCharacterController sut;

    @Mock
    private StarWarsIntegration starWarsIntegration;

    @Test
    public void shouldCallIntegration_whenCharacterIsRequested() {
        //when
        sut.getCharacter();

        //then
        Mockito.verify(starWarsIntegration).getStarWarsCharacter();
    }
}