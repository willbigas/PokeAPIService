package br.com.willbigas.pokeapiservice.server;

import br.com.willbigas.pokeapiservice.entity.dto.PokemonResponseDTO;
import br.com.willbigas.pokeapiservice.entity.dto.Result;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * Serviço de Pokemon
 * Classe será instanciada e executada, assim que a aplicação subir.
 */
@Component
public class PokemonServer {

    public static final String URL_PATH_GET_ALL_POKEMONS = "https://pokeapi.co/api/v2/pokemon/?limit=100000&offset=0";
    private final List<Result> results = new ArrayList<>();
    private Boolean requestFeito = false;

    public PokemonServer() {

        if (!requestFeito) {
            PokemonResponseDTO responseDTO = createRequest();
            processAndTransformRequest(responseDTO);
            requestFeito = true;
        }

    }

    private PokemonResponseDTO createRequest() {
        ResponseEntity<PokemonResponseDTO> exchange = new RestTemplate()
                .exchange(URL_PATH_GET_ALL_POKEMONS, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        return exchange.getBody();
    }

    private void processAndTransformRequest(PokemonResponseDTO response) {
        getResults().addAll(response.getResults().stream().toList());
    }

    public List<Result> getResults() {
        return results;
    }
}
