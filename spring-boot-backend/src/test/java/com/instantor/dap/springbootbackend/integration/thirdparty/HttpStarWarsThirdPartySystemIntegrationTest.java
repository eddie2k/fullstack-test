package com.instantor.dap.springbootbackend.integration.thirdparty;

import com.instantor.dap.springbootbackend.model.StarsWarsCharacter;
import com.instantor.dap.springbootbackend.properties.StarWarsApiProperties;
import com.instantor.dap.springbootbackend.testcategory.SystemIntegrationTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@Category(SystemIntegrationTest.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@ContextConfiguration(classes = StarWarsApiProperties.class, initializers = ConfigFileApplicationContextInitializer.class)
public class HttpStarWarsThirdPartySystemIntegrationTest {

    private static final Integer ANY = 1;

    private HttpStarWarsThirdParty sut;

    @Autowired
    private StarWarsApiProperties starWarsApiProperties;

    @Before
    public void setUp() {
        sut = new HttpStarWarsThirdParty(starWarsApiProperties);
    }

    @Test
    public void shouldRealIntegrationReturnMoreThan0_whenNumberOfAvailableCharacters() {
        //when
        int max = sut.getNumberOfAvailableCharacters();

        //then
        assertThat(max).isGreaterThan(0);
    }

    @Test
    public void shouldReturnCharacterWithName_whenCharacterIsRequested() {
        //when
        StarsWarsCharacter character = sut.getStarWarsCharacter(ANY);

        //then
        assertThat(character.getName()).isNotNull();
        assertThat(character.getName()).isNotBlank();
    }
}
