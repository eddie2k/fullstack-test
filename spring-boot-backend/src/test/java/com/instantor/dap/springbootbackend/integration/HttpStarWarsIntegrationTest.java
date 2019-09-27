package com.instantor.dap.springbootbackend.integration;

import com.instantor.dap.springbootbackend.integration.exception.StarWarsIntegrationException;
import com.instantor.dap.springbootbackend.integration.thirdparty.StarWarsThirdParty;
import com.instantor.dap.springbootbackend.integration.thirdparty.exception.StarWarsThirdPartyCommunicationException;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class HttpStarWarsIntegrationTest {

    private static final int ANY_NUMBER = 5;

    @InjectMocks
    private HttpStarWarsIntegration sut;

    @Mock
    private StarWarsThirdParty starWarsThirdParty;

    @Mock
    private RandomGenerator randomGenerator;

    @Captor
    private ArgumentCaptor<Integer> integerCaptor;

    @Test
    public void shouldUseIndexWithinLimits_lowerBound_whenCharacterIsRequested() {
        //given
        doReturn(ANY_NUMBER).when(starWarsThirdParty).getNumberOfAvailableCharacters();
        doReturn(1).when(randomGenerator).getInt(ANY_NUMBER);

        //when
        StarsWarsCharacter c = sut.getStarWarsCharacter();

        //then
        verify(starWarsThirdParty).getStarWarsCharacter(integerCaptor.capture());
        assertThat(integerCaptor.getValue()).isEqualTo(1);
    }

    @Test
    public void shouldUseIndexWithinLimits_upperBound_whenCharacterIsRequested() {
        //given
        int max = ANY_NUMBER;
        doReturn(max).when(starWarsThirdParty).getNumberOfAvailableCharacters();
        doReturn(max).when(randomGenerator).getInt(max);

        //when
        StarsWarsCharacter c = sut.getStarWarsCharacter();

        //then
        verify(starWarsThirdParty).getStarWarsCharacter(integerCaptor.capture());
        assertThat(integerCaptor.getValue()).isEqualTo(max);
    }

    @Test
    public void shouldThrowStarWarsIntegrationException_whenCharacterIsRequested_andThrowsStarWarsThirdPartyCommunicationException() {
        //given
        doThrow(StarWarsThirdPartyCommunicationException.class).when(starWarsThirdParty).getNumberOfAvailableCharacters();

        //then/when
        assertThatExceptionOfType(StarWarsIntegrationException.class).isThrownBy(() -> sut.getStarWarsCharacter());
    }

}