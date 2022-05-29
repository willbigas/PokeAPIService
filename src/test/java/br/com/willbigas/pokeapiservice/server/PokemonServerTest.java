package br.com.willbigas.pokeapiservice.server;

import br.com.willbigas.pokeapiservice.entity.dto.PokemonResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;

@DisplayName("Tests for Pokemon API")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonServerTest {

    @InjectMocks
    private static PokemonServer pokemonServer;

    @BeforeAll
    public static void beforeAll() {
        pokemonServer = new PokemonServer();
    }

    @Test
    void deveRetornarUmaResponseDTOAoRequisitarEndpointDaAPI() {
        Assertions.assertThat(pokemonServer.getPokemons())
                .isNotNull()
                .isNotEmpty();


    }

}