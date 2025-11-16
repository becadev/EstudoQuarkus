# Quarkus
Conhecido como java na nuvem.

## Criação de projeto
Existe duas formas de criação de projetos em quarkus, uma usando CLI (mais recomendado) e outra usando Maven.
O CLI foi feito exatamente para gerar, rodar e adicionar extensões ao Quarkus de forma simples. O Maven é mais verboso e exige uma configuração mais manual. Com o CLI toda a configuração, adição de extensão e estruturação do projeto são feitas apenas com um comando.


### CLI
O CLI foi feito exatamente para gerar, rodar e adicionar extensões ao Quarkus de forma simples.

✔ Vantagens do Quarkus CLI
- Não precisa decorar comandos enormes do Maven
- Evita erro de versão de dependências
- Adiciona extensões automaticamente no pom.xml corretamente
- Gera projetos limpos e sem warnings
- Funciona muito melhor para quem está começando
- Suporta dev mode mais rápido
- Atualiza dependências com um comando (./mvnw clean install)
- Dá feedback mais claro que o Maven

Comando para criar projeto usando CLI
``` bash
  quarkus create app com.becadev:person-api
```
Cria um projeto com artefato 'quarkus-personapi' e extensão REST + Hibernate Panache + JDBC Postgres
``` bash
  quarkus create app org.example:quarkus-personapi --extensions='resteasy-reactive-jackson,hibernate-orm-panache,agroal,quarkus-jdbc-postgresql' --no-code
```




Árvore de diretórios final esperada:
```bash
  quarkus-personapi/
  ├─ src/
  │  ├─ main/
  │  │  ├─ java/
  │  │  │  └─ org/example/
  │  │  │     ├─ Person.java            # entidade JPA (Panache)
  │  │  │     └─ PersonResource.java    # endpoints REST
  │  │  └─ resources/
  │  │     └─ application.properties    # configurações (DB, portas, etc)
  │  └─ test/
  │     └─ java/...
  ├─ mvnw
  ├─ mvnw.cmd
  ├─ pom.xml
  └─ README.md
```


### Pom.xml 
No arquivo *pom.xml* ficam todas configurações do projeto, extensões e dependencias que foram instaladas
```bash
```

## Comparação geral: Django x Quarkus

| **Django**               | **Quarkus**                                  |
|--------------------------|-----------------------------------------------|
| Model                    | Entity (JPA)                                  |
| Serializer               | Feito automaticamente pelo Jackson            |
| Views (FBVs/CBVs)        | Resources (REST Controllers)                  |
| URLs / routes            | `@Path`                                       |
| Services (se você cria)  | Services com `@ApplicationScoped`             |
| Migrations               | Flyway ou Liquibase                           |
| ORM                      | Hibernate ORM (JPA)                           |
| Middleware               | Interceptors / Filters                        |
| Validações               | Bean Validation (`@NotNull`, `@Email`, etc.)  |

