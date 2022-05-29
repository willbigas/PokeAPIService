package br.com.willbigas.pokeapiservice.server;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;

@DisplayName("Tests for Pokemon API")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokeAPIServerTest {

    @InjectMocks
    private static PokeAPIServer pokeAPIServer;

    @BeforeAll
    public static void beforeAll() {
        pokeAPIServer = new PokeAPIServer();
    }

    @Test
    void deveRetornarUmaResponseDTOAoRequisitarEndpointDaAPI() {
        Assertions.assertThat(pokeAPIServer.getResults())
                .isNotNull()
                .isNotEmpty();


    }

}