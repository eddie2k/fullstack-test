package com.instantor.dap.springbootbackend.controller.response;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacterMother;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class StarsWarsCharacterResponseBuilderTest {

    private static final StarsWarsCharacter ANY = StarsWarsCharacterMother.LUKE;

    private StarsWarsCharacterResponseBuilder sut;

    @Test
    public void shouldNotAllowResponseWithoutStatus() {
        //given
        sut = new StarsWarsCharacterResponseBuilder();
        sut.setStarsWarsCharacter(ANY);

        //when/then
        assertThatExceptionOfType(IllegalStateException.class)
                .isThrownBy(() -> sut.build());
    }
}