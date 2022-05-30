package br.com.willbigas.pokeapiservice.controller;

import br.com.willbigas.pokeapiservice.entity.Pokemon;
import br.com.willbigas.pokeapiservice.service.PokemonService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for Pokemon Controller")
class PokemonControllerTest {

    public static final String PIDGEY = "pidgey";
    public static final String PIKACHU = "pikachu";
    public static final String PIKACHU_GMAX = "pikachu-gmax";
    @InjectMocks
    private PokemonController pokemonController;

    @Mock
    private PokemonService pokemonService;

    @Test
    @DisplayName("findByName returns a list of pokemons when sucessful")
    void findByName_ReturnsListOfPokemons_WhenSucessful() {
        BDDMockito.when(pokemonService.findByName(ArgumentMatchers.eq("pi")))
                .thenReturn(List.of(new Pokemon(PIDGEY), new Pokemon(PIKACHU), new Pokemon(PIKACHU_GMAX)));

        String nameOfFisrtPokemon = new Pokemon(PIDGEY).getName();
        String nameOfSecondPokemon = new Pokemon(PIKACHU).getName();
        String nameOfThirdPokemon = new Pokemon(PIKACHU_GMAX).getName();

        List<Pokemon> pokemons = pokemonController.findByName("pi").getBody();

        Assertions.assertThat(pokemons)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3);

        Assertions.assertThat(pokemons.get(0).getName()).isEqualTo(nameOfFisrtPokemon);
        Assertions.assertThat(pokemons.get(1).getName()).isEqualTo(nameOfSecondPokemon);
        Assertions.assertThat(pokemons.get(2).getName()).isEqualTo(nameOfThirdPokemon);

    }

    @Test
    @DisplayName("findByName returns a empty list when find by 'ab' ")
    void findByName_ReturnsListEmptyOfPokemons_WhenSucessful() {
        BDDMockito.when(pokemonService.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Pokemon> pokemons = pokemonController.findByName("ab").getBody();

        Assertions.assertThat(pokemons)
                .isNotNull()
                .isEmpty();

    }


}