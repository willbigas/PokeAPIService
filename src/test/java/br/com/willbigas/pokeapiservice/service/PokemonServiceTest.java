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

    @Mock
    private PokemonService pokemonService;

    @BeforeEach
    void setup() {
        BDDMockito.when(pokemonService.findByName(Mockito.eq("charizard")))
                .thenReturn(List.of(new Pokemon("charizard", "url.com.br")));

        BDDMockito.when(pokemonService.findByName(Mockito.eq("pikachu")))
                .thenReturn(List.of(new Pokemon("pikachu", "url.com.br")));

        BDDMockito.when(pokemonService.findByName(Mockito.eq("i")))
                .thenReturn(List.of(new Pokemon("pikachu", "url.com.br"), new Pokemon("charizard", "url.com.br")));
    }

    @Test
    @DisplayName("findByName retorna um pokemon quando pesquisa por charizard")
    void findByName_RetornaUmPokemon_QuandoPesquisaPorCharizard() {
        String nomeDoPokemon = "charizard";
        List<Pokemon> pokemons = pokemonService.findByName(nomeDoPokemon);

        Assertions.assertThat(pokemons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(pokemons.get(0).getNome()).isEqualTo(nomeDoPokemon);

    }

    @Test
    @DisplayName("findByName retorna uma lista de pokemon quando pesquisa por i ordenado por nome")
    void findByName_RetornaUmaListaDePokemon_QuandoPesquisaPorI() {
        String texto = "i";
        String nomeDoPrimeiroPokemon = "pikachu";
        String nomeDoSegundoPokemon = "charizard";
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