# PokeAPIService
Api de Pokemons criado para avaliação de teste tecnico da empresa LooqBox

### [Regras do Desafio](https://github.com/looqbox/looqbox-backend-challenge#readme)
 
### JDK Utilizado
* JDK 17.0.3 LTS
* Spring Boot 2.7.0 (spring-boot-starter-web, spring-boot-devtools,spring-boot-starter-test) 
 
### Endpoint principal
* http://localhost:8080/pokemons?name={nome-do-pokemon}
* Exemplo -> http://localhost:8080/pokemons?name=pidge 

### Diagrama Arquitetural
![Screenshot](DIAGRAMA-ARQUITETURAL.png)


# Observações complementares

* Não usei nenhum tipo de banco de dados, pois na minha opnião não há necessidade com base nas necessidades.
* Tambem não utilizei nenhum pattern complexo, somente MVC comum, pois tbm não achei que havia necessidade pelo fato de ser um endpoint simples com uma regra de negocio simples.
