package br.com.willbigas.pokeapiservice.service;

import br.com.willbigas.pokeapiservice.entity.model.Pokemon;
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
        if (isNullOrEmpty()) {
            PokemonResponseDTO responseDTO = pokemonServer.criarRequisicao();
            pokemonServer.processarRequisicao(responseDTO);
        }
        return pokemonServer.getPokemons();
    }

    private boolean isNullOrEmpty() {
        return pokemonServer.getPokemons() == null || pokemonServer.getPokemons().isEmpty();
    }

    public List<Pokemon> findByName(String name) {
        List<Pokemon> pokemons = findAll();

        pokemons = pokemons.stream()
                .filter(p -> p.getNome().toLowerCase().startsWith(name.toLowerCase())).toList();
        pokemons = setHighlight(pokemons, name);
        pokemons = ordenar(pokemons);

        return pokemons;
    }

    /**
     * Varre as letras de cada nome do pokemon e atribui as tags '<pre> </pre>'
     *
     * @param pokemons
     * @param highlight
     * @return
     */
    public List<Pokemon> setHighlight(List<Pokemon> pokemons, String highlight) {

        int quantidadeDeLetrasDoHighlight = highlight.length();

        pokemons.forEach(p -> {

            StringBuilder nomeFormatado = new StringBuilder();
            nomeFormatado.append("<pre>");

            for (int i = 0; i < p.getNome().length(); i++) {
                char letraDoNomeDoPokemon = p.getNome().charAt(i);

                if (i == quantidadeDeLetrasDoHighlight) {
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

    /**
     * Cria multiplas ordenacoes para a lista do pokemon
     *
     * 1 -> Comprimento do nome do Pokémon em ordem crescente;
     * 2 -> Ordem alfabética do nome do Pokémon.
     *
     * @param pokemons
     * @return
     */
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
