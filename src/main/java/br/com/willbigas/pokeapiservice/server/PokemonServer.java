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

    public static final String URL_PATH_GET_ALL_POKEMONS = "https://pokeapi.co/api/v2/pokemon/?limit=100000&offset=0";
    private final List<Pokemon> pokemons = new ArrayList<>();

    public PokemonServer() {
        PokemonResponseDTO responseDTO = createRequest();
        processAndTransformRequest(responseDTO);
    }

    private PokemonResponseDTO createRequest() {
        ResponseEntity<PokemonResponseDTO> exchange = new RestTemplate()
                .exchange(URL_PATH_GET_ALL_POKEMONS, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
        return exchange.getBody();
    }

    private void processAndTransformRequest(PokemonResponseDTO response) {
        List<Result> resultados = response.getResults().stream().toList();
        resultados.forEach(result -> getPokemons().add(new Pokemon(result.getName())));
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
}
