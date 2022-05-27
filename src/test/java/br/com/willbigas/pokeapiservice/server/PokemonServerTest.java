package br.com.willbigas.pokeapiservice.server;

import br.com.willbigas.pokeapiservice.entity.dto.PokemonResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@DisplayName("Tests for Pokemon API")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PokemonServerTest {

    private static PokemonServer pokemonServer;

    @BeforeAll
    public static void beforeAll() {
        pokemonServer = new PokemonServer();
    }


    @Test
    void deveRetornarUmaResponseDTOAoRequisitarEndpointDaAPI() {
        PokemonResponseDTO responseDTO =  pokemonServer.criarRequisicao();
        pokemonServer.processarRequisicao(responseDTO);
        Assertions.assertThat( pokemonServer.getPokemons())
                .isNotNull()
                .isNotEmpty();


    }

}