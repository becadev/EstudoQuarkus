``` bash

src
└── main
    └── java
        └── org/example
            ├── domain
            │   ├── Aluno.java
            │   ├── Auxilio.java
            │   ├── Beneficio.java
            │   └── BeneficioAluno.java
            │
            ├── repository
            │   ├── AlunoRepository.java
            │   ├── AuxilioRepository.java
            │   └── BeneficioRepository.java
            │
            ├── service
            │   ├── AlunoService.java
            │   └── BeneficioService.java
            │
            ├── resource
            │   ├── AlunoResource.java
            │   ├── AuxilioResource.java
            │   └── BeneficioResource.java
            │
            ├── dto
            │   ├── AlunoDTO.java
            │   ├── BeneficioDTO.java
            │   └── AuxilioDTO.java
            │
            └── mapper
                ├── AlunoMapper.java
                └── BeneficioMapper.java
```

### Estrutura Quarkus(java):

| Pasta        | O que colocar         |
| ------------ | --------------------- |
| `domain`     | entidades JPA         |
| `resource`   | controllers REST      |
| `service`    | regra de negócio      |
| `repository` | acesso ao banco       |
| `dto`        | dados da API          |
| `mapper`     | mapear entidade ↔ DTO |


### Comparação de estruturas django(python) com estruturas do Quarkus(java):

| Pasta (Quarkus) | O que coloca                 | Equivalente no Django                                       |
| --------------- | ---------------------------- | ----------------------------------------------------------- |
| **domain**      | entidades JPA                | **models.py**                                               |
| **resource**    | controllers REST (endpoints) | **views.py** ou **ViewSets**                                |
| **service**     | regras de negócio            | **services.py** (separação opcional)                        |
| **repository**  | acesso ao banco (DAO)        | **ORM do Django + QuerySets** (não existe pasta repository) |
| **dto**         | objetos de transferência     | **serializers.py**                                          |
| **mapper**      | mapeamento entidade ↔ DTO    | **serializers.py** (faz papel de mapper)                    |




##  Atualizar o target 
No target ficam classees compiladas. Por isso ao alterar classes ou adicionar classes, é preciso rodar o comando de clean.
```bash
./mvnw clean compile # limpa e depois compila o projeto
ou
./mvnw clean quarkus:dev # limpa tudo e inicia o projeto em modo dev
ou 
./mvnw clean #  apenas limpa
```

### O que fica no target:
- código velho fica lá
- cache do Quarkus não atualiza
- metadata antiga do OpenAPI continua sendo usada
- jars sujos causam comportamentos estranhos