```bash

mvn spring-boot:run

 POST
 `http://localhost:8080/api/tarefas`
JSON


`http://localhost:8080/api/tarefas`

Listar
```bash

```

Deletar
curl -X DELETE http://localhost:8080/api/tarefas/6

Criar
```
curl -X POST http://localhost:8080/api/tarefas \
  -H "Content-Type: application/json" \
  -d '{"nome":"Tarefa Teste","descricao":"Testando API","dataEntrega":"2025-09-20","importante":false}'
```

Busca
bash ```
curl http://localhost:8080/api/tarefas/1
```

Atualizar apenas o status para FAZENDO
bash ```
curl -X PATCH http://localhost:8080/api/tarefas/5 \
  -H "Content-Type: application/json" \
  -d '{"importante": true}'
```
Atualizar descrição e data
bash```
curl -X PATCH http://localhost:8080/api/tarefas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "descricao": "Nova descrição atualizada",
    "dataEntrega": "2025-10-15"
  }'
```

Marcar importante e feito
bash```
curl -X PATCH http://localhost:8080/api/tarefas/1 \
  -H "Content-Type: application/json" \
  -d '{
    "status": "FEITO",
    "importante": true
  }'
  ```

Filtre por status A_FAZER
  bash ```
  curl http://localhost:8080/api/tarefas?status=A_FAZER
  curl "http://localhost:8080/api/tarefas?status=FEITO
  curl "http://localhost:8080/api/tarefas?status=FAZENDO
  ```

Filtre tarefas importantes
bash ```
curl http://localhost:8080/api/tarefas?importante=true
curl http://localhost:8080/api/tarefas?importante=false
```

Acesse: `http://localhost:8080/swagger-ui.html`


Acesse: `http://localhost:8080/h2-console`
  url `jdbc:h2:mem:testdb`
  usuário `sa`


