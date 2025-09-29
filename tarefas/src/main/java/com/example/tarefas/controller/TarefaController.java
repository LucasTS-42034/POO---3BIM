package com.example.tarefas.controller;

import com.example.tarefas.model.Status;
import com.example.tarefas.model.Tarefa;
import com.example.tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin(origins = "*")
public class TarefaController {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    // POST - Criar Tarefa
    @PostMapping
    public ResponseEntity<?> criarTarefa(@Valid @RequestBody Tarefa tarefa) {
        try {
            Tarefa novaTarefa = tarefaRepository.save(tarefa);
            return new ResponseEntity<>(novaTarefa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao criar tarefa: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // GET - Listar todas as tarefas (com filtros opcionais)
    @GetMapping
    public ResponseEntity<List<Tarefa>> listarTarefas(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Boolean importante) {
        
        try {
            List<Tarefa> tarefas;
            
            if (status != null && importante != null) {
                tarefas = tarefaRepository.findByStatusAndImportante(status, importante);
            } else if (status != null) {
                tarefas = tarefaRepository.findByStatus(status);
            } else if (importante != null) {
                tarefas = tarefaRepository.findByImportante(importante);
            } else {
                tarefas = tarefaRepository.findAll();
            }
            
            return new ResponseEntity<>(tarefas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaRepository.findById(id);
        
        if (tarefa.isPresent()) {
            return new ResponseEntity<>(tarefa.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Atualização parcial
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcialmente(@PathVariable Long id, 
                                                 @RequestBody Map<String, Object> updates) {
        try {
            Optional<Tarefa> tarefaOptional = tarefaRepository.findById(id);
            
            if (tarefaOptional.isEmpty()) {
                return new ResponseEntity<>("Tarefa não encontrada", HttpStatus.NOT_FOUND);
            }
            
            Tarefa tarefa = tarefaOptional.get();
            
            // Atualiza apenas os campos fornecidos
            updates.forEach((campo, valor) -> {
                switch (campo) {
                    case "nome":
                        tarefa.setNome((String) valor);
                        break;
                    case "descricao":
                        tarefa.setDescricao((String) valor);
                        break;
                    case "dataEntrega":
                        tarefa.setDataEntrega(java.time.LocalDate.parse((String) valor));
                        break;
                    case "importante":
                        tarefa.setImportante((Boolean) valor);
                        break;
                    case "status":
                        tarefa.setStatus(Status.valueOf((String) valor));
                        break;
                }
            });
            
            Tarefa tarefaAtualizada = tarefaRepository.save(tarefa);
            return new ResponseEntity<>(tarefaAtualizada, HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao atualizar tarefa: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // DELETE - Excluir tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTarefa(@PathVariable Long id) {
        try {
            if (tarefaRepository.existsById(id)) {
                tarefaRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Tarefa não encontrada", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao excluir tarefa: " + e.getMessage(), 
                                      HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
