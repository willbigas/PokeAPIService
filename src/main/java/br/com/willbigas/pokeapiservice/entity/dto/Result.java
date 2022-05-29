package br.com.willbigas.pokeapiservice.entity.dto;

public class Result {

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                '}';
    }
}
