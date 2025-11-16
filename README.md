# Quarkus-personapi

Projeto Quarkus REST API (Person) — instruções para criar e rodar localmente (Linux/Windows) usando PostgreSQL ou H2.
- [Mais sobre Quarkus e comparação com django](aboutQuarkus.md)
## Requisitos
- Java 17
- Maven (o projeto inclui wrapper `./mvnw`)
- PostgreSQL local ou Docker (opcional)
- Docker (opcional)

## Configuração do banco (Postgres)

Opção A — Docker (rápido, cross‑platform):
```bash
docker run --name quarkus-postgres \
  -e POSTGRES_DB=persondb -e POSTGRES_USER=dev -e POSTGRES_PASSWORD=dev \
  -p 5432:5432 -d postgres:15
```

Opção B — Postgres local (Linux):
```bash
# como superuser postgres
sudo -u postgres psql -c "CREATE USER dev WITH PASSWORD 'dev';"
sudo -u postgres psql -c "CREATE DATABASE persondb OWNER dev;"
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE persondb TO dev;"
```

Opção B — Postgres local (Windows PowerShell, assumindo psql no PATH):
```powershell
$env:PGPASSWORD="postgres"; psql -U postgres -h localhost -c "CREATE USER dev WITH PASSWORD 'dev';"
$env:PGPASSWORD="postgres"; psql -U postgres -h localhost -c "CREATE DATABASE persondb OWNER dev;"
```

## Variáveis de ambiente
Você pode usar o arquivo `.env` (exemplo abaixo) ou export/set as variáveis. Quarkus mapeia propriedades para variáveis de ambiente com prefixo `QUARKUS_` e `.` → `_`.

Exemplo `.env` (veja `.env.example` no projeto).

Linux/macOS:
```bash
export QUARKUS_DATASOURCE_DB_KIND=postgresql
export QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://localhost:5432/persondb
export QUARKUS_DATASOURCE_USERNAME=dev
export QUARKUS_DATASOURCE_PASSWORD=dev
```

Windows PowerShell:
```powershell
$env:QUARKUS_DATASOURCE_JDBC_URL="jdbc:postgresql://localhost:5432/persondb"
$env:QUARKUS_DATASOURCE_USERNAME="dev"
$env:QUARKUS_DATASOURCE_PASSWORD="dev"
```

## application.properties
O projeto traz `src/main/resources/application.properties`. Para production preferível usar variáveis de ambiente ou perfis. Para dev, ajuste as chaves conforme preferir.

Exemplo útil (dev):
```
quarkus.datasource.db-kind=postgresql
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/persondb
quarkus.datasource.username=dev
quarkus.datasource.password=dev
quarkus.hibernate-orm.database.generation=update
quarkus.http.port=8080
```

## Dependência JDBC (pom.xml)
Se usar Postgres, confirme que o pom contém o driver:
```xml
// filepath: /home/becadev/projetoQuarkus/quarkus-personapi/pom.xml
// ...existing code...
  <dependencies>
    <!-- ...existing dependencies... -->

    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-jdbc-postgresql</artifactId>
    </dependency>

    <!-- ...existing dependencies... -->
  </dependencies>
// ...existing code...
```

## Executando o projeto

Modo desenvolvimento (recomendado — live reload):
```bash
./mvnw clean install
./mvnw quarkus:dev
# abrir: http://localhost:8080
```

Se porta 8080 já em uso, troque:
```bash
./mvnw quarkus:dev -Dquarkus.http.port=8081
```

Build e rodar JAR:
```bash
./mvnw clean package -DskipTests
java -jar target/quarkus-app/quarkus-run.jar
```

## Endpoints (exemplo)
- Listar: GET  /persons
- Buscar: GET /persons/{id}
- Criar:  POST /persons
- Atualizar: PUT /persons/{id}
- Deletar: DELETE /persons/{id}

Exemplo curl:
```bash
curl -XPOST -H "Content-Type: application/json" -d '{"name":"João","age":30}' http://localhost:8080/persons
curl http://localhost:8080/persons
```

## Problemas comuns
- "Port 8080 in use": liste e mate o processo (ss/lsof + kill) ou mude a porta.
- "Unable to find a JDBC driver": adicione `quarkus-jdbc-postgresql` no pom.
- "detached entity passed to persist": não persista a entidade recebida se ela trouxer id; crie uma nova instância antes de persistir (veja PersonResource.java).
- Variáveis de configuração inválidas: verifique nomes das propriedades. Para logs SQL use `quarkus.hibernate-orm.log.sql=true`.

## Windows notes
- Use `./mvnw.cmd` no Windows (PowerShell/CMD).
- Se usar Docker no Windows, exponha a porta 5432 para o host.
- Para definir variáveis de ambiente permanentemente use `setx` (cmd) ou `$env:` (PowerShell para sessão atual).

---
Arquivo `.env.example` (exemplo) incluído no projeto para referência.
