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



Class de domínio BeneficioAluno
``` java 

    package org.example.domain;
    import java.time.LocalDate;
    import org.hibernate.annotations.CreationTimestamp; // já gera o id automaticamente

    import io.quarkus.hibernate.orm.panache.PanacheEntity;
    import jakarta.persistence.Entity;
    import jakarta.persistence.JoinColumn;
    import jakarta.persistence.ManyToOne;
    import jakarta.persistence.OneToOne;


    @Entity
    public class BeneficioAluno extends PanacheEntity {
        
        public String observacao;
        @CreationTimestamp // registra automaticamente quando é criado
        public LocalDate data_concessao;
        public Boolean ativo;

        @ManyToOne
        @JoinColumn(name = "aluno_id") // nome da coluna FK
        public Aluno aluno;

        @OneToOne
        @JoinColumn(name = "auxilio_id") // nome da coluna FK
        public Auxilio auxilio;

    }

```


Classe de dominio Beneficio

``` java 
package org.example.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Beneficio extends PanacheEntity {
    
    public String nome;
    public String descricao;
    public Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "auxilio_id") // nome da coluna FK
    public Auxilio auxilio;

}
```


Classe de domínio Auxilio
``` java
package org.example.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Auxilio extends PanacheEntity {
    public String titulo;
    public String descricao;
    public Boolean ativo;
}
```

classe de domínio Aluno

``` java
package org.example.domain;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;

@Entity
public class Aluno extends PanacheEntity{

    public String nome;
    public String email;
    public Boolean ativo;

}

```
