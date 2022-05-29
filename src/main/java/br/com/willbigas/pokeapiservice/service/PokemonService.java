package br.com.willbigas.pokeapiservice.service;

import br.com.willbigas.pokeapiservice.entity.model.Pokemon;
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
        return pokemonServer.getPokemons();
    }

    public List<Pokemon> findByName(String name) {
        List<Pokemon> pokemons = findAll();
        pokemons = filterByStartsWithPokemonName(name, pokemons);
        pokemons = setHighlight(pokemons, name);
        pokemons = sort(pokemons);

        return pokemons;
    }

    private List<Pokemon> filterByStartsWithPokemonName(String name, List<Pokemon> pokemons) {
        return pokemons.stream()
                .filter(p -> p.getName().toLowerCase().startsWith(name.toLowerCase())).toList();
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

            StringBuilder formattedName = new StringBuilder();
            formattedName.append("<pre>");

            for (int i = 0; i < p.getName().length(); i++) {
                char letterOfPokemonName = p.getName().charAt(i);

                if (i == quantidadeDeLetrasDoHighlight) {
                    formattedName.append("</pre>");
                }

                formattedName.append(letterOfPokemonName);
            }
            p.setHighlight(formattedName.toString());

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
    public List<Pokemon> sort(List<Pokemon> pokemons) {

        List<Pokemon> orderList = new ArrayList<>(pokemons);

        Collections.sort(orderList, new Comparator() {

            public int compare(Object o1, Object o2) {

                Integer x1 = ((Pokemon) o1).getName().length();
                Integer x2 = ((Pokemon) o2).getName().length();
                int lengthComparator = x1.compareTo(x2);

                if (lengthComparator != 0) {
                    return lengthComparator;
                }

                String s1 = ((Pokemon) o1).getName();
                String s2 = ((Pokemon) o2).getName();
                return s1.compareTo(s2);
            }
        });
        return orderList;
    }
}
