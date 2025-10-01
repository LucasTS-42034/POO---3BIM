package com.example.tarefa.controller;

import com.example.tarefa.dto.TarefaRequest;
import com.example.tarefa.dto.TarefaResponse;
import com.example.tarefa.model.Status;
import com.example.tarefa.model.Tarefa;
import com.example.tarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {
    
    @Autowired
    private TarefaService tarefaService;
    
    private TarefaResponse convertToResponse(Tarefa tarefa) {
        TarefaResponse response = new TarefaResponse();
        response.setId(tarefa.getId());
        response.setNome(tarefa.getNome());
        response.setDescricao(tarefa.getDescricao());
        response.setDataEntrega(tarefa.getDataEntrega());
        response.setImportante(tarefa.isImportante());
        response.setStatus(tarefa.getStatus());
        response.setDataCriacao(tarefa.getDataCriacao());
        return response;
    }
    
    // POST - Criar Tarefa
    @PostMapping
    public ResponseEntity<?> criarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest) {
        try {
            Tarefa tarefa = new Tarefa();
            tarefa.setNome(tarefaRequest.getNome());
            tarefa.setDescricao(tarefaRequest.getDescricao());
            tarefa.setDataEntrega(tarefaRequest.getDataEntrega());
            tarefa.setImportante(tarefaRequest.isImportante());
            
            Tarefa tarefaCriada = tarefaService.criarTarefa(tarefa);
            return new ResponseEntity<>(convertToResponse(tarefaCriada), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao criar tarefa: " + e.getMessage());
        }
    }
    
    // GET - Listar todas (com filtros opcionais)
    @GetMapping
    public ResponseEntity<List<TarefaResponse>> listarTarefas(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Boolean importante) {
        
        List<Tarefa> tarefas;
        
        if (status != null && importante != null) {
            tarefas = tarefaService.filtrarPorStatus(status);
            tarefas = tarefas.stream()
                    .filter(t -> t.isImportante() == importante)
                    .collect(Collectors.toList());
        } else if (status != null) {
            tarefas = tarefaService.filtrarPorStatus(status);
        } else if (importante != null) {
            tarefas = tarefaService.filtrarPorImportante(importante);
        } else {
            tarefas = tarefaService.listarTodas();
        }
        
        List<TarefaResponse> responses = tarefas.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }
    
    // GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Tarefa> tarefa = tarefaService.buscarPorId(id);
        return tarefa.map(t -> ResponseEntity.ok(convertToResponse(t)))
                .orElse(ResponseEntity.notFound().build());
    }
    
    // PATCH - Atualização parcial
    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcialmente(@PathVariable Long id, @RequestBody Tarefa tarefaAtualizada) {
        Tarefa tarefa = tarefaService.atualizarParcialmente(id, tarefaAtualizada);
        if (tarefa != null) {
            return ResponseEntity.ok(convertToResponse(tarefa));
        }
        return ResponseEntity.notFound().build();
    }
    
    // DELETE - Excluir tarefa
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        if (tarefaService.excluirTarefa(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
