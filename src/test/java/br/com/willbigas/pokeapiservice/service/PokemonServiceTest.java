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
                .thenReturn(List.of(new Pokemon("aron"), new Pokemon("abra")));
    }

    @Test
    @DisplayName("findByName return pokemon 'aron' when find by 'ar'")
    void findByName_returnPokemonAron_WhenFindByNameAr() {
        String pokemonName = "aron";
        List<Pokemon> pokemons = pokemonService.findByName("ar");
        pokemons = pokemonService.setHighlight(pokemons, "ar");

        Assertions.assertThat(pokemons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(pokemons.get(0).getName()).isEqualTo(pokemonName);

    }

    @Test
    @DisplayName("findByName retorna uma lista de pokemon quando pesquisa por a ordenado por tamanho e depois por nome")
    void findByName_ReturnListOrderBy_WhenFindByI() {
        String text = "a";
        String nameOfTheFirstPokemon = "abra";
        String nameOfTheSecondPokemon = "aron";
        List<Pokemon> pokemons = pokemonService.findByName(text);

        Assertions.assertThat(pokemons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);

        Assertions.assertThat(pokemons.get(0).getName()).isEqualTo(nameOfTheFirstPokemon);
        Assertions.assertThat(pokemons.get(1).getName()).isEqualTo(nameOfTheSecondPokemon);

    }

    @Test
    @DisplayName("findByName return empty when find by 'abc'")
    void findByName_RetunsEmpty_WhenFindABC() {
        String nameOfThePokemon = "abc";
        List<Pokemon> pokemons = pokemonService.findByName(nameOfThePokemon);

        Assertions.assertThat(pokemons)
                .isEmpty();
    }


}