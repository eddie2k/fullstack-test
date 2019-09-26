package com.instantor.dap.springbootbackend.controller;

import com.instantor.dap.springbootbackend.integration.StarWarsIntegration;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.instantor.dap.springbootbackend.model.StarsWarsCharacterMother.LUKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class StarWarsCharacterControllerTest {

    @InjectMocks
    private StarWarsCharacterController sut;

    @Mock
    private StarWarsIntegration starWarsIntegration;

    @Test
    public void shouldReturnLukeFromIntegration_whenCharacterIsRequested() {
        //given
        doReturn(LUKE).when(starWarsIntegration).getStarWarsCharacter();

        //when
        StarsWarsCharacter c = sut.getCharacter();

        //then
        assertThat(c).isEqualTo(LUKE);
    }
}