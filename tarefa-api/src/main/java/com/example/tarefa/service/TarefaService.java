package com.example.tarefa.service;

import com.example.tarefa.model.Status;
import com.example.tarefa.model.Tarefa;
import com.example.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {
    
    @Autowired
    private TarefaRepository tarefaRepository;
    
    public Tarefa criarTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }
    
    public List<Tarefa> listarTodas() {
        return tarefaRepository.findAll();
    }
    
    public List<Tarefa> filtrarPorStatus(Status status) {
        return tarefaRepository.findByStatus(status);
    }
    
    public List<Tarefa> filtrarPorImportante(boolean importante) {
        return tarefaRepository.findByImportante(importante);
    }
    
    public Optional<Tarefa> buscarPorId(Long id) {
        return tarefaRepository.findById(id);
    }
    
    public Tarefa atualizarParcialmente(Long id, Tarefa tarefaAtualizada) {
        return tarefaRepository.findById(id)
                .map(tarefa -> {
                    if (tarefaAtualizada.getNome() != null) {
                        tarefa.setNome(tarefaAtualizada.getNome());
                    }
                    if (tarefaAtualizada.getDescricao() != null) {
                        tarefa.setDescricao(tarefaAtualizada.getDescricao());
                    }
                    if (tarefaAtualizada.getDataEntrega() != null) {
                        tarefa.setDataEntrega(tarefaAtualizada.getDataEntrega());
                    }
                    if (tarefaAtualizada.getStatus() != null) {
                        tarefa.setStatus(tarefaAtualizada.getStatus());
                    }
                    tarefa.setImportante(tarefaAtualizada.isImportante());
                    
                    return tarefaRepository.save(tarefa);
                })
                .orElse(null);
    }
    
    public boolean excluirTarefa(Long id) {
        if (tarefaRepository.existsById(id)) {
            tarefaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
