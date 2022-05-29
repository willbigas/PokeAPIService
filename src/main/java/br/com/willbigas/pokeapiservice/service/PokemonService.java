package br.com.willbigas.pokeapiservice.service;

import br.com.willbigas.pokeapiservice.repository.PokemonRepository;
import br.com.willbigas.pokeapiservice.entity.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public List<Pokemon> findByName(String name) {
        List<Pokemon> pokemons = findAll();
        pokemons = filterByStartsWithPokemonName(name, pokemons);
        pokemons = setHighlightWithSubstring(pokemons, name);
        //        pokemons = setHighlight(pokemons, name);
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

        int lengthOfHighlight = highlight.length();

        pokemons.forEach(p -> {

            StringBuilder formattedName = new StringBuilder();
            formattedName.append("<pre>");

            for (int i = 0; i < p.getName().length(); i++) {
                char letterOfPokemonName = p.getName().charAt(i);

                if (i == lengthOfHighlight) {
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
     * Varre as letras com Substring e atribui o código '<pre> </pre>'
     * @param pokemons
     * @param highlight
     * @return
     */
    public List<Pokemon> setHighlightWithSubstring(List<Pokemon> pokemons, String highlight) {

        pokemons.forEach(p -> {

            StringBuilder formattedName = new StringBuilder();
            formattedName.append("<pre>");

            String firstPart = p.getName().substring(0, highlight.length());
            String secondPart = p.getName().substring(highlight.length());

            formattedName.append(firstPart);
            formattedName.append("</pre>");
            formattedName.append(secondPart);

            p.setHighlight(formattedName.toString());

        });
        return pokemons;
    }


    /**
     * Cria multiplas ordenacoes para a lista do pokemon
     * <p>
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
