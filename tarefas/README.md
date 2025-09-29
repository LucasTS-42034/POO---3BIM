# API de Gerenciamento de Tarefas

Esta é uma API REST desenvolvida em Spring Boot para gerenciamento de tarefas (Tarefas). Permite criar, listar, atualizar e excluir tarefas com filtros por status e importância.

## Tecnologias Utilizadas

- **Spring Boot 3.x**: Framework principal
- **Spring Data JPA**: Para persistência de dados
- **H2 Database**: Banco de dados em memória para testes
- **Spring Web**: Para criação da API REST
- **Spring Validation**: Para validação de dados

## Como Executar a Aplicação

### Pré-requisitos

- Java 17 ou superior
- Maven 3.x

### Passos para Execução

1. **Navegue até o diretório do projeto:**
   ```bash
   cd tarefas
   ```

2. **Execute a aplicação:**
   ```bash
   ./mvnw spring-boot:run
   ```
   Ou no Windows:
   ```
   cmd mvnw.cmd spring-boot:run
   ```

3. **A aplicação estará rodando em:** `http://localhost:8080`

## Endpoints da API

### 1. Criar Tarefa
- **Método:** `POST`
- **URL:** `/api/tarefas`
- **Corpo da Requisição (JSON):**
  ```json
  {
    "nome": "Preparar slides de JPA",
    "descricao": "Relacionamentos e exemplo Many-to-Many",
    "dataEntrega": "2025-09-01",
    "importante": true
  }
  ```
- **Resposta:** Status 201 (Created) com o objeto da tarefa criada
- **Observações:**
  - `id` e `dataCriacao` são gerados automaticamente
  - `status` inicia como `A_FAZER` se não informado

### 2. Listar Todas as Tarefas
- **Método:** `GET`
- **URL:** `/api/tarefas`
- **Parâmetros de Query (opcionais):**
  - `status`: Filtrar por status (`A_FAZER`, `FAZENDO`, `FEITO`)
  - `importante`: Filtrar por importância (`true` ou `false`)
- **Exemplos:**
  - `/api/tarefas` - Lista todas
  - `/api/tarefas?status=FAZENDO` - Apenas tarefas em andamento
  - `/api/tarefas?importante=true` - Apenas tarefas importantes
  - `/api/tarefas?status=A_FAZER&importante=true` - Combinação de filtros

### 3. Buscar Tarefa por ID
- **Método:** `GET`
- **URL:** `/api/tarefas/{id}`
- **Exemplo:** `/api/tarefas/1`
- **Resposta:** Status 200 com detalhes da tarefa ou 404 se não encontrada

### 4. Atualizar Tarefa Parcialmente
- **Método:** `PATCH`
- **URL:** `/api/tarefas/{id}`
- **Corpo da Requisição (JSON):** Apenas os campos a serem atualizados
  ```json
  {
    "status": "FAZENDO",
    "descricao": "Adicionar parte de JPQL e Criteria"
  }
  ```
- **Campos atualizáveis:** `nome`, `descricao`, `dataEntrega`, `importante`, `status`

### 5. Excluir Tarefa
- **Método:** `DELETE`
- **URL:** `/api/tarefas/{id}`
- **Resposta:** Status 204 (No Content) se excluída ou 404 se não encontrada

## Modelo de Dados

### Tarefa
```json
{
  "id": 1,
  "nome": "Nome da tarefa",
  "descricao": "Descrição opcional",
  "dataCriacao": "2025-09-28T20:27:54.886372",
  "dataEntrega": "2025-09-01",
  "importante": true,
  "status": "A_FAZER"
}
```

### Status
- `A_FAZER`: Tarefa a fazer
- `FAZENDO`: Tarefa em andamento
- `FEITO`: Tarefa concluída

## Validações

- `nome`: Campo obrigatório
- `dataEntrega`: Formato `yyyy-MM-dd`
- `status`: Deve ser um dos valores do enum

## Banco de Dados H2

A aplicação utiliza H2 em memória para testes. Para acessar o console:

1. Abra o navegador em: `http://localhost:8080/h2-console`
2. **JDBC URL:** `jdbc:h2:mem:testdb`
3. **User:** `sa`
4. **Password:** (deixe em branco)

## Testes com Thunder Client

### Teste 1: Criar Tarefa
- **URL:** `http://localhost:8080/api/tarefas`
- **Método:** POST
- **Body:**
  ```json
  {
    "nome": "Preparar slides de JPA",
    "descricao": "Relacionamentos e exemplo Many-to-Many",
    "dataEntrega": "2025-09-01",
    "importante": true
  }
  ```

### Teste 2: Listar Todas
- **URL:** `http://localhost:8080/api/tarefas`
- **Método:** GET

### Teste 3: Filtrar por Status
- **URL:** `http://localhost:8080/api/tarefas?status=FAZENDO`
- **Método:** GET

### Teste 4: Filtrar por Importância
- **URL:** `http://localhost:8080/api/tarefas?importante=true`
- **Método:** GET

### Teste 5: Buscar por ID
- **URL:** `http://localhost:8080/api/tarefas/1`
- **Método:** GET

### Teste 6: Atualização Parcial
- **URL:** `http://localhost:8080/api/tarefas/1`
- **Método:** PATCH
- **Body:**
  ```json
  {
    "status": "FAZENDO",
    "descricao": "Adicionar parte de JPQL e Criteria"
  }
  ```

### Teste 7: Excluir Tarefa
- **URL:** `http://localhost:8080/api/tarefas/1`
- **Método:** DELETE

## Tratamento de Erros

A API retorna mensagens de erro apropriadas para:
- Campos obrigatórios não preenchidos
- Tarefas não encontradas
- Dados inválidos

## Desenvolvimento

Para contribuir ou modificar:
1. Clone o repositório
2. Faça as alterações necessárias
3. Execute os testes
4. Envie um pull request

## Autor

Desenvolvido como parte da avaliação bimestral de Programação Orientada a Objetos.
