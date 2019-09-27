package com.instantor.dap.springbootbackend.integration;

import com.instantor.dap.springbootbackend.integration.thirdparty.StarWarsThirdParty;
import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.properties.StarWarsApiProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HttpStarWarsIntegrationTest {

    private static final String ANY = "ANY";
    private static final int ANY_NUMBER = 5;
    private static final int ANOTHER_NUMBER = 5;

    private HttpStarWarsIntegration sut;

    @Mock
    private StarWarsThirdParty starWarsThirdParty;

    @Mock
    private RandomGenerator randomGenerator;

    @Captor
    private ArgumentCaptor<Integer> integerCaptor;

    @Before
    public void setUp() {
        StarWarsApiProperties starWarsApiProperties;
        starWarsApiProperties = new StarWarsApiProperties();
        starWarsApiProperties.setEndpoint(ANY);
        starWarsApiProperties.setCharacterspath(ANY);

        sut = new HttpStarWarsIntegration(starWarsThirdParty, starWarsApiProperties, randomGenerator);
    }

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

}