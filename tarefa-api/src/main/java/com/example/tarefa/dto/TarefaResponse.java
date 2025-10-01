package com.example.tarefa.dto;

import com.example.tarefa.model.Status;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TarefaResponse {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataEntrega;
    private boolean importante;
    private Status status;
    private LocalDateTime dataCriacao;
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }
    
    public boolean isImportante() { return importante; }
    public void setImportante(boolean importante) { this.importante = importante; }
    
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    
    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }
}
