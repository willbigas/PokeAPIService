# PokeAPIService
API de Pokemons criado para avaliação com base em um desafio técnico para a empresa LooqBox

### [Regras do Desafio](https://github.com/looqbox/looqbox-backend-challenge#readme)
 
### JDK e Dependências
* JDK 17.0.3 LTS
* Spring Boot 2.7.0 (spring-boot-starter-web, spring-boot-devtools,spring-boot-starter-test) 
 
### Endpoint principal
* http://localhost:8080/pokemons?name={nome-do-pokemon}
* Exemplo -> http://localhost:8080/pokemons?name=pidge 

### Diagrama Arquitetural
![Screenshot](DIAGRAMA-ARQUITETURAL.png)


# Observações complementares (decisões de implementação)

* Não usei nenhum tipo de banco de dados, pois na minha opnião não há necessidade com base nos requisitos da aplicação.
* Criei um repositório em memoria para simular a necessidade de um banco de dados de pokemons.
* Não encontrei necessidade de usar 'Design Patterns complexos' pelo fato de ser um endpoint simples com uma regra de negocio simples, utilizei um MVC comum.
* Não utilizei docker, pois não tenho experiência com gradle e obtive alguns erros na tentativa, como o gradle é um requisito obrigatório do desafio, priorizei o gradle em vez do docker.
