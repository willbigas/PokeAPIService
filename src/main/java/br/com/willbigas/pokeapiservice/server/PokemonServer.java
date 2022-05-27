package br.com.willbigas.pokeapiservice.server;

import br.com.willbigas.pokeapiservice.entity.model.Pokemon;
import br.com.willbigas.pokeapiservice.entity.dto.PokemonResponseDTO;
import br.com.willbigas.pokeapiservice.entity.dto.Result;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Component
public class PokemonServer {

    private final List<Pokemon> pokemons = new ArrayList<>();

    public PokemonResponseDTO criarRequisicao() {
        ResponseEntity<PokemonResponseDTO> exchange = new RestTemplate()
                .exchange("https://pokeapi.co/api/v2/pokemon/?limit=100000&offset=0", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    public void processarRequisicao(PokemonResponseDTO response) {
        List<Result> resultados = response.getResults().stream().toList();
        resultados.forEach(result -> getPokemons().add(new Pokemon(result.getName(), result.getUrl())));
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
