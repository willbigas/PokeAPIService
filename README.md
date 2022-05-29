# PokeAPIService
API de Pokemons criado para avaliação técnica com base nos requisitos apresentados pela empresa LooqBox

#### [Regras do Desafio](https://github.com/looqbox/looqbox-backend-challenge#readme)
 
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
* Criei um repositório em memoria para simular a necessidade de um banco de dados de pokemons, uma abordagem mais performatica seria persistir no banco em operações distintas.
* Não encontrei necessidade de usar outros 'Design Patterns' pelo fato de ser um endpoint simples com uma regra de negocio simples, utilizei **MVC** comum.
* Não utilizei **Docker**, pois não tenho experiência com **Gradle** e obtive alguns erros na tentativa de implementação, como o gradle é um requisito obrigatório do desafio e o docker não, priorizei o gradle em vez do docker.


### Todas as decisões foram implementadas de acordo com 3 principios que eu gosto de seguir independente de qualquer arquitetura que estou implementando (Monolito, Microserviços)

* DRY (Don't repeat yourself), 
* KISS (Keep it simple and stupid)
* YAGNI (You ain't gonna need it).
