package br.com.willbigas.pokeapiservice;

import br.com.willbigas.pokeapiservice.entity.dto.Result;
import br.com.willbigas.pokeapiservice.entity.model.Pokemon;
import br.com.willbigas.pokeapiservice.server.PokemonServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {


    private final PokemonServer pokemonServer;

    private final List<Pokemon> pokemons = new ArrayList<>();

    @Autowired
    public PokemonRepository(PokemonServer pokemonServer) {
        this.pokemonServer = pokemonServer;
    }

    public List<Pokemon> findAll() {
        if (pokemons.isEmpty()) {
            transformResultsToPokemons(pokemonServer.getResults());
        }
        return pokemons;
    }

    private void transformResultsToPokemons(List<Result> results) {
        results.forEach(result -> pokemons.add(new Pokemon(result.getName())));
    }


}
