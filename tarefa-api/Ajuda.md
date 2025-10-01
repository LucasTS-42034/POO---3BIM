# Apresentação da API de Gerenciamento de Tarefas

## Introdução
Esta apresentação demonstra uma API REST completa para gerenciamento de tarefas, desenvolvida com Spring Boot. A API permite criar, listar, filtrar, atualizar e excluir tarefas, utilizando JPA para persistência em banco H2.

## Tecnologias Utilizadas
- **Spring Boot 3.x**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para mapeamento objeto-relacional e operações de banco.
- **H2 Database**: Banco de dados em memória para desenvolvimento.
- **Spring Validation**: Para validação de dados de entrada.
- **SpringDoc OpenAPI**: Para documentação automática da API (Swagger).
- **Maven**: Gerenciamento de dependências e build.

## Estrutura do Projeto
- **Model**: Entidade `Tarefa` com campos como nome, descrição, dataEntrega, importante, status, dataCriacao.
- **DTOs**: `TarefaRequest` para entrada, `TarefaResponse` para saída.
- **Repository**: Interface `TarefaRepository` com queries customizadas.
- **Service**: Lógica de negócio em `TarefaService`.
- **Controller**: Endpoints REST em `TarefaController`.
- **Config**: Configuração do Swagger.

## Como Executar a Aplicação
1. Navegue até a pasta `tarefa-api`.
2. Execute: `mvnw.cmd spring-boot:run` (Windows).
3. A aplicação inicia em http://localhost:8080.

## Demonstração dos Endpoints

### 1. Listar Todas as Tarefas
- **Comando:** `curl -X GET http://localhost:8080/api/tarefas`
- **Resultado:** Lista as 3 tarefas iniciais carregadas do `data.sql`.

### 2. Criar uma Nova Tarefa
- **Comando:**
  ```
  curl -X POST http://localhost:8080/api/tarefas -H "Content-Type: application/json" -d "{\"nome\": \"Nova Tarefa\", \"descricao\": \"Descrição da tarefa\", \"dataEntrega\": \"2025-12-01\", \"importante\": true}"
  ```
- **Resultado:** Status 201 com a tarefa criada, incluindo ID gerado.

### 3. Filtrar por Status
- **Comando:** `curl -X GET "http://localhost:8080/api/tarefas?status=FAZENDO"`
- **Resultado:** Apenas tarefas com status FAZENDO.

### 4. Buscar por ID
- **Comando:** `curl -X GET http://localhost:8080/api/tarefas/1`
- **Resultado:** Detalhes da tarefa com ID 1.

### 5. Atualizar Parcialmente
- **Comando:**
  ```
  curl -X PATCH http://localhost:8080/api/tarefas/1 -H "Content-Type: application/json" -d "{\"status\": \"FEITO\"}"
  ```
- **Resultado:** Tarefa atualizada com status alterado.

### 6. Excluir Tarefa
- **Comando:** `curl -X DELETE http://localhost:8080/api/tarefas/1`
- **Resultado:** Status 204, tarefa removida.

## Interfaces Web

### Swagger UI
- **URL:** http://localhost:8080/swagger-ui.html
- **Descrição:** Interface interativa para testar todos os endpoints. Permite visualizar a documentação e executar requests diretamente no navegador.

### H2 Console
- **URL:** http://localhost:8080/h2-console
- **Credenciais:**
  - JDBC URL: `jdbc:h2:mem:testdb`
  - User: `sa`
  - Password: (vazio)
- **Descrição:** Console para visualizar e executar queries SQL no banco H2.

## Validações Implementadas
- Campos obrigatórios: `nome` e `dataEntrega`.
- Formato de data: yyyy-MM-dd.
- Mensagens de erro personalizadas para validações.

## Dados Iniciais
A aplicação carrega automaticamente 3 tarefas de exemplo:
- "Estudar Spring Boot" (A_FAZER, importante)
- "Fazer exercícios JPA" (FAZENDO, importante)
- "Revisar projeto API" (FEITO, não importante)

## Conclusão
Esta API demonstra o uso completo do ecossistema Spring para desenvolvimento de aplicações RESTful, com boas práticas como separação de responsabilidades, validação de dados, documentação automática e testes. Está pronta para expansão com autenticação, paginação e outras funcionalidades avançadas.
