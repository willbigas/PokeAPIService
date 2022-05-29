package br.com.willbigas.pokeapiservice.server;

import br.com.willbigas.pokeapiservice.server.dto.PokemonResponseDTO;
import br.com.willbigas.pokeapiservice.server.dto.Result;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe responsável por consumir a API -> PokeAPI (https://pokeapi.co/)
 *
 * Classe ficará disponivel para consumo após a aplicação subir, guardando o objeto de pokemon na variavel results,
 * podendo ser chamada de qualquer lugar da aplicação
 */
@Component
public class PokeAPIServer {

    public static final String URL_PATH_GET_ALL_POKEMONS = "https://pokeapi.co/api/v2/pokemon/?limit=100000&offset=0";
    private List<Result> results = new ArrayList<>();

    public PokeAPIServer() {
        PokemonResponseDTO responseDTO = createRequest();
        results = transformResponseToResults(responseDTO);
    }

    private PokemonResponseDTO createRequest() {
        ResponseEntity<PokemonResponseDTO> exchange = new RestTemplate()
                .exchange(URL_PATH_GET_ALL_POKEMONS, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    private List<Result> transformResponseToResults(PokemonResponseDTO response) {
        return new ArrayList<>(response.getResults().stream().toList());
    }

    public List<Result> getResults() {
        return results;
    }
}
