package br.com.willbigas.pokeapiservice.service;

import br.com.willbigas.pokeapiservice.entity.Pokemon;
import br.com.willbigas.pokeapiservice.entity.dto.PokemonResponseDTO;
import br.com.willbigas.pokeapiservice.server.PokemonServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PokemonService {

    private final PokemonServer pokemonServer;

    @Autowired
    public PokemonService(PokemonServer pokemonServer) {
        this.pokemonServer = pokemonServer;
    }

    public List<Pokemon> findAll() {
        if (pokemonServer.getPokemons() == null || pokemonServer.getPokemons().isEmpty()) {
            PokemonResponseDTO responseDTO = pokemonServer.criarRequisicao();
            pokemonServer.processarRequisicao(responseDTO);
        }
        return pokemonServer.getPokemons();
    }

    public List<Pokemon> findByName(String name) {
        List<Pokemon> pokemons = findAll();
        pokemons = pokemons.stream().filter(p -> p.getNome().toLowerCase().startsWith(name.toLowerCase())).toList();
        pokemons = setHighlight(pokemons, name);
        pokemons = ordenar(pokemons);
        return pokemons;
    }

    public List<Pokemon> setHighlight(List<Pokemon> pokemons, String highlight) {

        int tamanhoDoHighlight = highlight.length();

        pokemons.forEach(p -> {

            StringBuilder nomeFormatado = new StringBuilder();
            nomeFormatado.append("<pre>");

            for (int i = 0; i < p.getNome().length(); i++) {
                char letraDoNomeDoPokemon = p.getNome().charAt(i);

                if (i == tamanhoDoHighlight) {
                    nomeFormatado.append("</pre>");
                }

                nomeFormatado.append(letraDoNomeDoPokemon);
            }
            p.setHighlight(nomeFormatado.toString());

            if (!p.getHighlight().contains("</pre>")) {
                p.setHighlight(p.getHighlight() + "</pre>");
            }
        });
        return pokemons;
    }

    public List<Pokemon> ordenar(List<Pokemon> pokemons) {

        List<Pokemon> listaOrdenada = new ArrayList<>(pokemons);

        Collections.sort(listaOrdenada, new Comparator() {

            public int compare(Object o1, Object o2) {

                Integer x1 = ((Pokemon) o1).getNome().length();
                Integer x2 = ((Pokemon) o2).getNome().length();
                int lengthComparator = x1.compareTo(x2);

                if (lengthComparator != 0) {
                    return lengthComparator;
                }

                String s1 = ((Pokemon) o1).getNome();
                String s2 = ((Pokemon) o2).getNome();
                return s1.compareTo(s2);
            }
        });
        return listaOrdenada;
    }
}
