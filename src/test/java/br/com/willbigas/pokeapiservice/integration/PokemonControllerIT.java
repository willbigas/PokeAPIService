package br.com.willbigas.pokeapiservice.integration;

import br.com.willbigas.pokeapiservice.entity.Pokemon;
import br.com.willbigas.pokeapiservice.repository.PokemonRepository;
import br.com.willbigas.pokeapiservice.server.dto.PokemonResponseDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PokemonControllerIT {

    @Autowired
    @Qualifier(value = "testRestTemplateDefault")
    private TestRestTemplate testRestTemplate;

    @TestConfiguration
    @Lazy
    static class Config {

        @Bean(name = "testRestTemplateDefault")
        public TestRestTemplate testRestTemplateRoleUserCreator(@Value("${local.server.port}") int port) {
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder()
                    .rootUri("http://localhost:" + port);
            return new TestRestTemplate(restTemplateBuilder);
        }
    }

    @Test
    @DisplayName("findByName returns a list of pokemons when sucessful")
    void findByName_ReturnsListOfPokemons_WhenSucessful() {

        String name = "charizard";

        String url = String.format("/pokemons/?name=%s", name);
        ResponseEntity<List<Pokemon>> exchange = testRestTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        Assertions.assertThat(exchange.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(exchange.getBody())
                .isNotNull()
                .isNotEmpty()
                .hasSize(4);



        Assertions.assertThat(exchange.getBody().get(0).getName()).isEqualTo("charizard");

    }

    @Test
    @DisplayName("findByName returns empty list when find by 'abc'")
    void findByName_ReturnsListEmptyList_WhenFindByAbc() {

        String name = "abc";

        String url = String.format("/pokemons/?name=%s", name);
        ResponseEntity<List<Pokemon>> exchange = testRestTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        Assertions.assertThat(exchange.getStatusCode())
                .isEqualTo(HttpStatus.OK);

        Assertions.assertThat(exchange.getBody())
                .isNotNull()
                .isEmpty();
    }
}
