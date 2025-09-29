package com.example.tarefas.repository;

import com.example.tarefas.model.Status;
import com.example.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatus(Status status);
    List<Tarefa> findByImportante(boolean importante);
    List<Tarefa> findByStatusAndImportante(Status status, boolean importante);
}
