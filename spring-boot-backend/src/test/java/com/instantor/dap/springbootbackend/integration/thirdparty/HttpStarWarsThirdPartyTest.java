package com.instantor.dap.springbootbackend.integration.thirdparty;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static com.instantor.dap.springbootbackend.model.StarsWarsCharacterMother.LUKE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class HttpStarWarsThirdPartyTest {

    private static final Integer ANY = 1;

    @InjectMocks
    private HttpStarWarsThirdParty sut;

    @Test
    public void shouldReturn5AvailableCharacters_whenNumberOfAvailableCharacters() {
        //when
        int i = sut.getNumberOfAvailableCharacters();

        //then
        assertThat(i).isEqualTo(5);//TODO hardcoded for now
    }

    @Test
    public void shouldReturnLuke_whenCharacterIsRequested() {
        //when
        StarsWarsCharacter c = sut.getStarWarsCharacter(ANY);

        //then
        assertThat(c).isEqualTo(LUKE);//TODO hardcoded for now
    }
}