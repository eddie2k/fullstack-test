package com.instantor.dap.springbootbackend.controller;

import com.instantor.dap.springbootbackend.integration.StarWarsIntegration;
import com.instantor.dap.springbootbackend.integration.exception.IntegrationFailureException;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacterMother;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class StarWarsCharacterControllerTest {

    private static final StarsWarsCharacter ANY = StarsWarsCharacterMother.LUKE;

    @InjectMocks
    private StarWarsCharacterController sut;

    @Mock
    private StarWarsIntegration starWarsIntegration;

    @Test
    public void shouldReturnIntegrationOk_whenIntegrationWorks() {
        //given
        doReturn(ANY).when(starWarsIntegration).getStarWarsCharacter();

        //when
        StarsWarsCharacterResponse response = sut.getCharacter();

        //then
        assertThat(response.getIntegrationOk()).isTrue();
    }

    @Test
    public void shouldReturnLukeFromIntegration_whenCharacterIsRequested() {
        //given
        doReturn(StarsWarsCharacterMother.LUKE).when(starWarsIntegration).getStarWarsCharacter();

        //when
        StarsWarsCharacterResponse response = sut.getCharacter();

        //then
        assertThat(response.getStarWarsCharacter()).isEqualTo(StarsWarsCharacterMother.LUKE);
    }

    @Test
    public void shouldReturnIntegrationFailed_whenIntegrationFails() {
        //given
        doThrow(IntegrationFailureException.class).when(starWarsIntegration).getStarWarsCharacter();

        //when
        StarsWarsCharacterResponse response = sut.getCharacter();

        //then
        assertThat(response.getIntegrationOk()).isFalse();
    }

    @Test
    public void shouldNotReturnCharacter_whenIntegrationFails() {
        //given
        doThrow(IntegrationFailureException.class).when(starWarsIntegration).getStarWarsCharacter();

        //when
        StarsWarsCharacterResponse response = sut.getCharacter();

        //then
        assertThat(response.getStarWarsCharacter()).isNull();
    }
}