package br.com.willbigas.pokeapiservice.repository;

import br.com.willbigas.pokeapiservice.server.dto.Result;
import br.com.willbigas.pokeapiservice.entity.Pokemon;
import br.com.willbigas.pokeapiservice.server.PokeAPIServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PokemonRepository {


    private final PokeAPIServer pokeAPIServer;

    private final List<Pokemon> pokemons = new ArrayList<>();

    @Autowired
    public PokemonRepository(PokeAPIServer pokeAPIServer) {
        this.pokeAPIServer = pokeAPIServer;
    }

    public List<Pokemon> findAll() {
        if (pokemons.isEmpty()) {
            transformResultsToPokemons(pokeAPIServer.getResults());
        }
        return pokemons;
    }

    private void transformResultsToPokemons(List<Result> results) {
        results.forEach(result -> pokemons.add(new Pokemon(result.getName())));
    }


}
