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
* Criei um repositório em memoria para simular um banco de dados de pokemons, uma abordagem mais performatica seria persistir no banco e consultar em operações distintas.
* Não encontrei necessidade de usar outros 'Design Patterns' pelo fato de ser um endpoint simples com uma regra de negocio simples, utilizei **MVC** comum.
* Não utilizei **Docker** na aplicação, pois não tenho experiência com **Gradle** e obtive alguns erros na tentativa de implementação, como o Gradle é um requisito obrigatório do desafio e o Docker não, priorizei o Gradle em vez do docker.


#### Todas as decisões de implementação foram desenvolvidas com base em 3 principios que eu gosto de seguir independente de arquitetura (Monolito, Microserviços, Hexagonal)

* [DRY (Don't repeat yourself)](https://www.google.com.br/search?q=dry+principle)
* [KISS (Keep it simple and stupid)](https://www.google.com.br/search?q=kiss+principle)
* [YAGNI (You ain't gonna need it)](https://www.google.com.br/search?q=yagni+principle)

