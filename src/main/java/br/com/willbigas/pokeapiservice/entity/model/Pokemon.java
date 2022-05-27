package br.com.willbigas.pokeapiservice.entity.model;

import java.util.Objects;

public class Pokemon {

    private String nome;
    private String url;
    private String highlight;

    public Pokemon(String nome, String url) {
        this.nome = nome;
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHighlight() {
        return highlight;
    }

    public void setHighlight(String highlight) {
        this.highlight = highlight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(url, pokemon.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
