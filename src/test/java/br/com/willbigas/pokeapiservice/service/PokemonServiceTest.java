package br.com.willbigas.pokeapiservice.service;

import br.com.willbigas.pokeapiservice.entity.model.Pokemon;
import br.com.willbigas.pokeapiservice.server.PokemonServer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Pokemon Service")
class PokemonServiceTest {

    @InjectMocks
    private PokemonService pokemonService;

    @Mock
    private PokemonServer pokemonServer;

    @BeforeEach
    void setup() {
        BDDMockito.when(pokemonServer.getPokemons())
                .thenReturn(List.of(new Pokemon("aron", "url.com.br"), new Pokemon("abra", "url.com.br")));
    }

    @Test
    @DisplayName("findByName retorna o pokemon aron quando pesquisa por ar")
    void findByName_RetornaUmPokemon_QuandoPesquisaPorCharizard() {
        String nomeDoPokemon = "aron";
        List<Pokemon> pokemons = pokemonService.findByName("ar");
        pokemons = pokemonService.setHighlight(pokemons, "ar");

        Assertions.assertThat(pokemons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(pokemons.get(0).getNome()).isEqualTo(nomeDoPokemon);

    }

    @Test
    @DisplayName("findByName retorna uma lista de pokemon quando pesquisa por a ordenado por tamanho e depois por nome")
    void findByName_RetornaUmaListaDePokemon_QuandoPesquisaPorI() {
        String texto = "a";
        String nomeDoPrimeiroPokemon = "abra";
        String nomeDoSegundoPokemon = "aron";
        List<Pokemon> pokemons = pokemonService.findByName(texto);

        Assertions.assertThat(pokemons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);

        Assertions.assertThat(pokemons.get(0).getNome()).isEqualTo(nomeDoPrimeiroPokemon);
        Assertions.assertThat(pokemons.get(1).getNome()).isEqualTo(nomeDoSegundoPokemon);

    }

    @Test
    @DisplayName("findByName retorna vazio quando pesquisa por abc")
    void findByName_RetornaVazio_QuandoPesquisaPorAbc() {
        String nomeDoPokemon = "abc";
        List<Pokemon> pokemons = pokemonService.findByName(nomeDoPokemon);

        Assertions.assertThat(pokemons)
                .isEmpty();
    }


}