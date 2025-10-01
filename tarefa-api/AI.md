# Instruções para Usar a Aplicação Tarefa API

## Visão Geral
Esta é uma API REST para gerenciamento de tarefas, construída com Spring Boot, JPA e H2 Database. Permite criar, listar, filtrar, atualizar e excluir tarefas.

## Como Executar
1. Certifique-se de ter Java 17+ e Maven instalados.
2. Navegue até a pastan `tarefa-api`.
3. Execute: `mvw.cmd spring-boot:run` (Windows) ou `./mvnw spring-boot:run` (Linux/Mac).
4. A aplicação estará rodando em http://localhost:8080.

## Endpoints Disponíveis

### 1. Criar Tarefa
- **Método:** POST
- **URL:** /api/tarefas
- **Descrição:** Cria uma nova tarefa no sistema. Os campos `id`, `status` e `dataCriacao` são gerados automaticamente pelo sistema. O `status` inicia como `A_FAZER` se não for informado.
- **Body (JSON):**
  ```json
  {
    "nome": "Preparar slides de JPA",
    "descricao": "Relacionamentos e exemplo Many-to-Many",
    "dataEntrega": "2025-09-01",
    "importante": true
  }  
  ```
- **Comando curl:**
  ```bash
  curl -X POST http://localhost:8080/api/tarefas -H "Content-Type: application/json" -d '{"nome": "Preparar slides de JPA", "descricao": "Relacionamentos e exemplo Many-to-Many", "dataEntrega": "2025-09-01", "importante": true}'
  ```
- **Resposta esperada:** Status 201 (Created) com o JSON da tarefa criada, incluindo o ID gerado.

### 2. Listar Todas as Tarefas
- **Método:** GET
- **URL:** /api/tarefas
- **Descrição:** Retorna uma lista de todas as tarefas cadastradas no sistema, incluindo todos os campos como `id`, `nome`, `descricao`, `dataEntrega`, `importante`, `status` e `dataCriacao`.
- **Comando curl:**
  ```bash
  curl -X GET http://localhost:8080/api/tarefas
  ```
- **Resposta esperada:** Status 200 (OK) com um array JSON contendo todas as tarefas.

### 3. Filtrar por Status
- **Método:** GET
- **URL:** /api/tarefas?status=FAZENDO
- **Descrição:** Filtra e retorna apenas as tarefas que possuem o status especificado. Os valores possíveis para `status` são: `A_FAZER`, `FAZENDO`, `FEITO`.
- **Comando curl:**
  ```bash
  curl -X GET "http://localhost:8080/api/tarefas?status=FAZENDO"
  ```
- **Resposta esperada:** Status 200 (OK) com um array JSON das tarefas filtradas pelo status.

### 4. Filtrar por Importante
- **Método:** GET
- **URL:** /api/tarefas?importante=true
- **Descrição:** Filtra e retorna apenas as tarefas marcadas como importantes (`true`) ou não importantes (`false`).
- **Comando curl:**
  ```bash
  curl -X GET "http://localhost:8080/api/tarefas?importante=true"
  ```
- **Resposta esperada:** Status 200 (OK) com um array JSON das tarefas filtradas.

### 5. Buscar por ID
- **Método:** GET
- **URL:** /api/tarefas/{id}
- **Descrição:** Busca e retorna os detalhes completos de uma tarefa específica pelo seu ID único.
- **Exemplo:** /api/tarefas/1
- **Comando curl:**
  ```bash
  curl -X GET http://localhost:8080/api/tarefas/1
  ```
- **Resposta esperada:** Status 200 (OK) com o JSON da tarefa encontrada, ou 404 (Not Found) se o ID não existir.

### 6. Atualização Parcial
- **Método:** PATCH
- **URL:** /api/tarefas/{id}
- **Descrição:** Permite atualizar parcialmente uma tarefa existente. Envie apenas os campos que deseja alterar; os demais permanecem inalterados. Campos atualizáveis: `nome`, `descricao`, `dataEntrega`, `importante`, `status`.
- **Body (JSON):** Apenas os campos a atualizar.
  ```json
  {
    "status": "FAZENDO",
    "descricao": "Adicionar parte de JPQL e Criteria"
  }
  ```
- **Comando curl:**
  ```bash
  curl -X PATCH http://localhost:8080/api/tarefas/1 \
    -H "Content-Type: application/json" \
    -d '{
      "status": "FAZENDO",
      "descricao": "Adicionar parte de JPQL e Criteria"
    }'
  ```
- **Resposta esperada:** Status 200 (OK) com o JSON da tarefa atualizada, ou 404 (Not Found) se o ID não existir.

### 7. Excluir Tarefa
- **Método:** DELETE
- **URL:** /api/tarefas/{id}
- **Descrição:** Remove permanentemente uma tarefa do banco de dados pelo seu ID.
- **Exemplo:** /api/tarefas/1
- **Comando curl:**
  ```bash
  curl -X DELETE http://localhost:8080/api/tarefas/1
  ```
- **Resposta esperada:** Status 204 (No Content) se a exclusão for bem-sucedida, ou 404 (Not Found) se o ID não existir.

## URLs Úteis
- **API Base:** http://localhost:8080/api/tarefas
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **H2 Console:** http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:testdb
  - User: sa
  - Password: (vazio)

## Validações
- `nome` e `dataEntrega` são obrigatórios.
- Datas no formato yyyy-MM-dd (sem fuso horário).
- Mensagens de erro amigáveis para campos inválidos.

## Testando com Thunder Client
1. Abra o Thunder Client no VSCode.
2. Crie uma nova requisição.
3. Selecione o método (GET, POST, etc.) e digite a URL.
4. Para POST/PATCH: Vá na aba Body, selecione JSON e insira o conteúdo.
5. Clique em Send.
6. Verifique o status (200/201 para sucesso) e o JSON de retorno.

## Dados Iniciais
A aplicação carrega 3 tarefas de exemplo ao iniciar:
- Estudar Spring Boot (A_FAZER, importante)
- Fazer exercícios JPA (FAZENDO, importante)
- Revisar projeto API (FEITO, não importante)

## Tecnologias
- Spring Boot 3.x
- Spring Data JPA
- H2 Database
- Spring Validation
- SpringDoc OpenAPI (Swagger)



Vou explicar passo a passo de forma mais clara como executar a aplicação:

## 🚀 **Como Executar - Passo a Passo Simplificado**

### **Pré-requisitos:**
1. **Java 17** instalado
2. **Maven** instalado
3. **VSCode** com extensão Thunder Client (opcional)

---

## 📥 **Passo 1: Criar o Projeto**

### **Opção A: Via Spring Initializr (Recomendado)**
1. Acesse: https://start.spring.io/
2. Configure exatamente como na imagem abaixo:
   - **Project:** Maven
   - **Language:** Java
   - **Spring Boot:** 3.1.5
   - **Group:** com.example
   - **Artifact:** tarefa-api
   - **Java:** 17

3. **Adicione as Dependencies:**
   - Spring Web
   - Spring Data JPA
   - H2 Database
   - Validation

4. Clique em **"Generate"** e baixe o ZIP

5. Extraia o ZIP em uma pasta chamada `tarefa-api`

### **Opção B: Usar Estrutura Pronta**
Crie manualmente a estrutura de pastas que mostrei anteriormente.

---

## 📁 **Passo 2: Adicionar os Arquivos**

Na pasta `tarefa-api/src/main/java/com/example/tarefa/`, crie as seguintes pastas e arquivos:

```
tarefa-api/
└── src/
    └── main/
        ├── java/
        │   └── com/
        │       └── example/
        │           └── tarefa/
        │               ├── TarefaApplication.java
        │               ├── config/
        │               │   └── SwaggerConfig.java
        │               ├── controller/
        │               │   └── TarefaController.java
        │               ├── model/
        │               │   ├── Tarefa.java
        │               │   └── Status.java
        │               ├── repository/
        │               │   └── TarefaRepository.java
        │               ├── service/
        │               │   └── TarefaService.java
        │               └── dto/
        │                   ├── TarefaRequest.java
        │                   └── TarefaResponse.java
        └── resources/
            ├── application.properties
            └── data.sql
```

**Cole o código de cada arquivo** conforme fornecido na minha resposta anterior.

---

## 🏃 **Passo 3: Executar a Aplicação**

### **No Terminal/CMD:**
```bash
# Navegue até a pasta do projeto
cd tarefa-api

# Execute (Windows)
mvnw.cmd spring-boot:run

# Ou execute (Linux/Mac)
./mvnw spring-boot:run

# Se tiver Maven instalado globalmente:
mvn spring-boot:run
```

### **No VSCode:**
1. Abra a pasta `tarefa-api` no VSCode
2. Abra o terminal integrado (Ctrl + `)
3. Execute: `mvn spring-boot:run`

---

## ✅ **Passo 4: Verificar se Está Funcionando**

Você deve ver no terminal:
```
Started TarefaApplication in X.XXX seconds
```

---

## 🧪 **Passo 5: Testar a API**

### **Método 1: Thunder Client (VSCode)**
1. Instale a extensão "Thunder Client" no VSCode
2. Ctrl+Shift+P → "Thunder Client: New Request"
3. Teste os endpoints:

**Exemplo - Criar Tarefa:**
- **Método:** POST
- **URL:** `http://localhost:8080/api/tarefas`
- **Body:** JSON
```json
{
    "nome": "Minha primeira tarefa",
    "descricao": "Descrição da tarefa",
    "dataEntrega": "2025-09-15",
    "importante": true
}
```

### **Método 2: Navegador**
Acesse: `http://localhost:8080/api/tarefas`

### **Método 3: curl (Terminal)**
```bash
# Listar todas as tarefas
curl http://localhost:8080/api/tarefas

# Criar tarefa
curl -X POST http://localhost:8080/api/tarefas \
  -H "Content-Type: application/json" \
  -d '{"nome":"Tarefa Teste","descricao":"Testando API","dataEntrega":"2025-09-20","importante":true}'
```

---

## 🔍 **Passo 6: Acessar Ferramentas Adicionais**

### **Swagger UI (Documentação):**
Acesse: `http://localhost:8080/swagger-ui.html`

### **H2 Console (Banco de Dados):**
Acesse: `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `sa`
- **Password:** (deixe vazio)

---

## ❌ **Solução de Problemas Comuns**

### **Erro: "mvnw não encontrado"**
- Use `mvn` em vez de `mvnw`
- Ou instale o Maven: https://maven.apache.org/install.html

### **Erro: Porta 8080 em uso**
Altere no `application.properties`:
```properties
server.port=8081
```

### **Erro: Java não encontrado**
- Verifique instalação do Java: `java -version`
- Deve ser Java 17 ou superior

### **Erro de compilação**
- Verifique se todos os arquivos estão nas pastas corretas
- Execute: `mvn clean compile`

---

## 📋 **Checklist de Verificação**

- [ ] Projeto criado no Spring Initializr
- [ ] Estrutura de pastas criada
- [ ] Todos os arquivos .java colocados nas pastas corretas
- [ ] application.properties configurado
- [ ] Terminal aberto na pasta `tarefa-api`
- [ ] Comando `mvn spring-boot:run` executado
- [ ] Mensagem "Started TarefaApplication" aparece
- [ ] API responde em `http://localhost:8080/api/tarefas`

---

## 🎯 **Teste Rápido**

Execute este comando para verificar se está funcionando:
```bash
curl http://localhost:8080/api/tarefas
```

**Resposta esperada:** Lista de tarefas em JSON (pode ser vazio ou com dados de exemplo)

Se ainda estiver com dificuldades, me diga em qual passo específico está travando!