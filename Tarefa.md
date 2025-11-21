# 📚 API de Biblioteca Comunitária (Quarkus)  
Tema para estudo e prática completa de estrutura de projeto, entidades, serviços, regras de negócio e endpoints.

---

## 🎯 Objetivo do Projeto
Criar uma API em **Quarkus** para gerenciar o funcionamento básico de uma biblioteca comunitária, permitindo:

- Cadastro de livros
- Cadastro de autores
- Gestão de empréstimos
- Aplicação de regras reais de negócio
- Validações
- Relacionamentos entre entidades
- Migrations com Flyway (opcional)

Esse projeto cobre praticamente tudo que uma API profissional exige.

---

# 🧱 Estrutura de Domínio

## 📘 1. Livro (Book)
**Atributos:**
- `id`
- `titulo`
- `anoPublicacao`
- `categoria`
- `autor` *(ManyToOne)*

---

## ✍️ 2. Autor (Author)
**Atributos:**
- `id`
- `nome`
- `nacionalidade`
- `livros` *(OneToMany)*

---

## 📦 3. Empréstimo (Loan)
**Atributos:**
- `id`
- `livro`
- `dataEmprestimo`
- `dataDevolucao`
- `status` (`EM_ABERTO` ou `DEVOLVIDO`)

---

# 🧠 Regras de Negócio

### 🔹 Regra 1 — Não permitir empréstimo se o livro já estiver emprestado
Se já existe um empréstimo **EM_ABERTO** para o livro → lançar erro.

### 🔹 Regra 2 — Só permitir devolução se o empréstimo estiver EM_ABERTO
Não pode devolver empréstimos já finalizados.

### 🔹 Regra 3 — Ao cadastrar um livro, o autor já deve existir
Evita inconsistências no banco.

### 🔹 Regra 4 — Um autor não pode ser deletado se tiver livros cadastrados
Garantir integridade dos dados.

---

# 🧰 Endpoints Sugeridos

## 🟦 `/books`
| Método | Endpoint         | Descrição                    |
|--------|------------------|------------------------------|
| GET    | /books           | Lista todos os livros        |
| POST   | /books           | Cria um novo livro           |
| GET    | /books/{id}      | Busca um livro específico    |
| PUT    | /books/{id}      | Atualiza um livro            |
| DELETE | /books/{id}      | Remove um livro              |

---

## 🟩 `/authors`
| Método | Endpoint           | Descrição                     |
|--------|--------------------|-------------------------------|
| GET    | /authors           | Lista os autores              |
| POST   | /authors           | Cria um autor                 |
| GET    | /authors/{id}      | Busca um autor                |
| DELETE | /authors/{id}      | Remove um autor (regra 4)     |

---

## 🟨 `/loans`
| Método | Endpoint                 | Descrição                               |
|--------|---------------------------|------------------------------------------|
| POST   | /loans                    | Cria um empréstimo (regra 1)             |
| PUT    | /loans/{id}/return        | Realiza devolução (regra 2)              |
| GET    | /loans                    | Lista todos os empréstimos               |

---

# 🗂️ Estrutura de Pastas Sugerida

```bash
    src/
    └── main/
    ├── java/
    │ └── com/becadev/library/
    │ ├── entity/
    │ │ ├── Author.java
    │ │ ├── Book.java
    │ │ └── Loan.java
    │ ├── resource/
    │ │ ├── AuthorResource.java
    │ │ ├── BookResource.java
    │ │ └── LoanResource.java
    │ ├── service/
    │ │ ├── AuthorService.java
    │ │ ├── BookService.java
    │ │ └── LoanService.java
    │ └── exception/
    │ └── BusinessException.java
    └── resources/
    ├── application.properties
    └── db/migration/ (para Flyway)
```