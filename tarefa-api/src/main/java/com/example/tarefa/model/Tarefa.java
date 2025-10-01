package com.example.tarefa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false)
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "A data de entrega é obrigatória")
    @Column(name = "data_entrega", nullable = false)
    private LocalDate dataEntrega;
    
    private boolean importante = false;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.A_FAZER;
    
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    
    @PrePersist
    protected void onCreate() {
        dataCriacao = LocalDateTime.now();
    }
    
    public Tarefa() {}
    
    public Tarefa(String nome, String descricao, LocalDate dataEntrega, boolean importante) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataEntrega = dataEntrega;
        this.importante = importante;
    }
    
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
