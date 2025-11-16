# Relacionamento em Quarkus(Java)


### 1. OneToOne
```java
  @Entity
  public class Pessoa {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      public Long id;
  
      public String nome;
  
      @OneToOne
      @JoinColumn(name = "documento_id")
      public Documento documento;
  }
```

```java 
  @Entity
  public class Documento {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      public Long id;
  
      public String cpf;
  }
```

### 2. OneToMany
o famoso related_name do django 
``` java
  @Entity
  public class Autor {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      public Long id;
  
      public String nome;
  
      @OneToMany(mappedBy = "autor")
      public List<Livro> livros;
  }
```

### 3. ManyToMany
```java
  @Entity
  public class Aluno {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      public Long id;
  
      public String nome;
  
      @ManyToMany
      @JoinTable(
          name = "aluno_curso",
          joinColumns = @JoinColumn(name = "aluno_id"),
          inverseJoinColumns = @JoinColumn(name = "curso_id")
      )
      public Set<Curso> cursos;
  }
```

```java
  @Entity
  public class Curso {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      public Long id;
  
      public String nome;
  
      @ManyToMany(mappedBy = "cursos")
      public Set<Aluno> alunos;
  }
```

### 4. ManyToOne
```java
  @Entity
  public class Livro {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      public Long id;
  
      public String titulo;
  
      @ManyToOne
      @JoinColumn(name = "autor_id") // nome da coluna FK
      public Autor autor;
  }
```
```java
  @Entity
  public class Autor {
  
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      public Long id;
  
      public String nome;
  }
```


## 📌 Regras importantes no Quarkus/JPA
1. Sempre colocar mappedBy no lado "não dono" da relação
> Ele evita que o Hibernate crie tabelas duplicadas.

2. Evitar List em ManyToMany → usar Set
> Performático e evita duplicados.

3. Sempre usar @Transactional no Service quando altera o banco
