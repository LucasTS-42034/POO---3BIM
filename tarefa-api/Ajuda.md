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














# 🐧 **Como Executar no Linux - Guia Completo**

## ✅ **Pré-requisitos - Verificar Instalações**

### **1. Verificar Java**
```bash
java -version
```
**Saída esperada:**
```
openjdk version "17.0.9" 2023-10-17
OpenJDK Runtime Environment (build 17.0.9+9)
OpenJDK 64-Bit Server VM (build 17.0.9+9, mixed mode)
```

### **2. Verificar Maven**
```bash
mvn -version
```
**Saída esperada:**
```
Apache Maven 3.9.5
Maven home: /usr/share/maven
Java version: 17.0.9, vendor: Ubuntu, runtime: /usr/lib/jvm/java-17-openjdk-amd64
```

---

## 🔧 **Instalação (Se Necessário)**

### **Instalar Java 17**
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# Verificar instalação
java -version
```

### **Instalar Maven**
```bash
# Ubuntu/Debian
sudo apt install maven

# Verificar instalação
mvn -version
```

---

## 📥 **Preparação do Projeto**

### **1. Criar Diretório do Projeto**
```bash
# Navegar para onde quer criar o projeto
cd ~/Documents  # ou outro diretório de sua preferência

# Criar pasta do projeto
mkdir tarefa-api
cd tarefa-api
```

### **2. Estrutura de Pastas (Manual)**
```bash
# Criar estrutura completa
mkdir -p src/main/java/com/example/tarefa/{config,controller,model,repository,service,dto}
mkdir -p src/main/resources
mkdir -p src/test/java/com/example/tarefa/{controller,service}

# Verificar estrutura criada
find . -type f -name "*.java" | head -10
```

### **3. Opção: Usar Spring Initializr via Terminal**
```bash
# Baixar projeto pronto do Spring Initializr
curl https://start.spring.io/starter.zip \
  -d dependencies=web,data-jpa,h2,validation \
  -d groupId=com.example \
  -d artifactId=tarefa-api \
  -d name=tarefa-api \
  -d description="API de Gerenciamento de Tarefas" \
  -d packageName=com.example.tarefa \
  -d packaging=jar \
  -d javaVersion=17 \
  -o tarefa-api.zip

# Extrair
unzip tarefa-api.zip -d tarefa-api
cd tarefa-api
```

---

## 🗂️ **Criar os Arquivos no Linux**

### **Método 1: Usando echo (Rápido)**
```bash
# application.properties
cat > src/main/resources/application.properties << 'EOF'
# Server
server.port=8080

# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Date Format
spring.mvc.format.date=yyyy-MM-dd
spring.jackson.date-format=yyyy-MM-dd
spring.jackson.time-zone=America/Sao_Paulo

# Swagger
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
EOF

# data.sql
cat > src/main/resources/data.sql << 'EOF'
INSERT INTO tarefas (nome, descricao, data_entrega, importante, status, data_criacao) VALUES
('Estudar Spring Boot', 'Aprender conceitos básicos do Spring Boot', '2025-09-15', true, 'A_FAZER', CURRENT_TIMESTAMP),
('Fazer exercícios JPA', 'Praticar relacionamentos e queries', '2025-09-10', true, 'FAZENDO', CURRENT_TIMESTAMP),
('Revisar projeto API', 'Revisar código e documentação', '2025-09-05', false, 'FEITO', CURRENT_TIMESTAMP);
EOF
```

### **Método 2: Usando Editor (Recomendado)**
```bash
# Instalar editor (se não tiver)
sudo apt install nano

# Criar/copiar cada arquivo individualmente
nano src/main/java/com/example/tarefa/TarefaApplication.java
# [Cole o conteúdo do arquivo, Ctrl+X, Y, Enter para salvar]
```

---

## 🏃 **Execução da Aplicação**

### **1. Dar Permissões ao Maven Wrapper**
```bash
# Se estiver usando mvnw (Maven Wrapper)
chmod +x mvnw

# Verificar permissões
ls -la mvnw
```

### **2. Compilar e Executar**
```bash
# Opção A: Usando Maven Wrapper (recomendado)
./mvnw spring-boot:run

# Opção B: Usando Maven instalado
mvn spring-boot:run

# Opção C: Compilar primeiro, depois executar
./mvnw clean compile
./mvnw spring-boot:run
```

### **3. Verificar Execução**
```bash
# Em outro terminal, testar se está rodando
curl http://localhost:8080/api/tarefas

# Ou usar wget
wget -q -O - http://localhost:8080/api/tarefas
```

---

## 🧪 **Testes no Linux**

### **Testes com curl**
```bash
# 1. Listar todas as tarefas
curl http://localhost:8080/api/tarefas

# 2. Criar nova tarefa
curl -X POST http://localhost:8080/api/tarefas \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Estudar Linux",
    "descricao": "Aprender comandos básicos",
    "dataEntrega": "2025-09-25",
    "importante": true
  }'

# 3. Buscar por ID
curl http://localhost:8080/api/tarefas/1

# 4. Filtrar por status
curl "http://localhost:8080/api/tarefas?status=A_FAZER"

# 5. Atualização parcial
curl -X PATCH http://localhost:8080/api/tarefas/1 \
  -H "Content-Type: application/json" \
  -d '{"status": "FEITO"}'

# 6. Excluir tarefa
curl -X DELETE http://localhost:8080/api/tarefas/1
```

### **Testes com scripts automatizados**
```bash
# Criar script de teste
cat > test-api.sh << 'EOF'
#!/bin/bash

echo "=== TESTANDO API DE TAREFAS ==="

# URL base
BASE_URL="http://localhost:8080/api/tarefas"

echo "1. Listando todas as tarefas:"
curl -s $BASE_URL | jq .

echo -e "\n2. Criando nova tarefa:"
curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Tarefa Teste Linux",
    "descricao": "Criada via script",
    "dataEntrega": "2025-09-30",
    "importante": false
  }' | jq .

echo -e "\n3. Verificando tarefa criada:"
curl -s $BASE_URL | jq .
EOF

# Dar permissão e executar
chmod +x test-api.sh
./test-api.sh
```

---

## 🔍 **Monitoramento e Debug**

### **Verificar Processo Java**
```bash
# Ver processos Java rodando
jps -l

# Ver uso de memória
ps aux | grep java

# Ver logs em tempo real (se aplicação estiver rodando)
tail -f /path/to/application.log
```

### **Testar Conexão**
```bash
# Verificar se porta está aberta
netstat -tulpn | grep 8080

# Ou usando ss
ss -tulpn | grep 8080

# Testar conectividade
telnet localhost 8080
```

---

## 🛠️ **Solução de Problemas Comuns no Linux**

### **Problema: Java não encontrado**
```bash
# Verificar JAVA_HOME
echo $JAVA_HOME

# Configurar JAVA_HOME (adicionar ao ~/.bashrc)
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH

# Recarregar
source ~/.bashrc
```

### **Problema: Porta 8080 ocupada**
```bash
# Encontrar processo usando a porta
sudo lsof -i :8080

# Matar processo (se necessário)
sudo kill -9 <PID>

# Ou mudar porta no application.properties
echo "server.port=8081" >> src/main/resources/application.properties
```

### **Problema: Permissões do mvnw**
```bash
# Corrigir permissões
chmod +x mvnw
chmod +x .mvn/wrapper/maven-wrapper.jar
```

### **Problema: Erro de compilação**
```bash
# Limpar e recompilar
./mvnw clean compile

# Ou forçar download das dependências
./mvnw dependency:resolve
```

### **Problema: Memória insuficiente**
```bash
# Executar com mais memória
./mvnw spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx512m -Xms256m"
```

---

## 📊 **Comandos Úteis para Desenvolvimento**

### **Desenvolvimento com Hot Reload**
```bash
# Instalar dependência para devtools (no pom.xml)
# Executar com restart automático
./mvnw spring-boot:run -Dspring-boot.run.fork=false
```

### **Empacotar para Produção**
```bash
# Criar JAR executável
./mvnw clean package

# Executar JAR
java -jar target/tarefa-api-1.0.0.jar
```

### **Ver Dependências**
```bash
# Listar todas as dependências
./mvnw dependency:tree

# Verificar atualizações
./mvnw versions:display-dependency-updates
```

---

## 🌐 **Acessando no Navegador Linux**

### **URLs para Testar:**
```bash
# Abrir no navegador padrão
xdg-open http://localhost:8080/api/tarefas
xdg-open http://localhost:8080/swagger-ui.html
xdg-open http://localhost:8080/h2-console
```

### **Configuração H2 Console:**
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **User Name:** `sa`
- **Password:** (deixe vazio)

---

## ✅ **Checklist Final Linux**

- [ ] Java 17+ instalado e configurado
- [ ] Maven instalado ou mvnw com permissões
- [ ] Estrutura de pastas criada
- [ ] Arquivos de código colocados nos lugares corretos
- [ ] Aplicação compila sem erros (`./mvnw compile`)
- [ ] Aplicação inicia (`./mvnw spring-boot:run`)
- [ ] API responde (`curl http://localhost:8080/api/tarefas`)
- [ ] H2 Console acessível
- [ ] Swagger UI funcionando

---

## 🚀 **Comando Rápido para Executar**
```bash
# Sequência completa (assumindo que projeto já está preparado)
cd tarefa-api
chmod +x mvnw
./mvnw clean spring-boot:run
```

**Saída esperada no final:**
```
Started TarefaApplication in 3.456 seconds (process running for 3.789)
```

Agora sua API está rodando no Linux! 🎉