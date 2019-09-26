package com.instantor.dap.springbootbackend.integration;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.instantor.dap.springbootbackend.model.StarsWarsCharacterMother.LUKE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HttpStarWarsIntegrationTest {

    @InjectMocks
    private HttpStarWarsIntegration sut;

    @Test
    public void shouldReturnLuke_whenCharacterIsRequested() {
        //when
        StarsWarsCharacter c = sut.getStarWarsCharacter();

        //then
        assertThat(c).isEqualTo(LUKE);
    }

}