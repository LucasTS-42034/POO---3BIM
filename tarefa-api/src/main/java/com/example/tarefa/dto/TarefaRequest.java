package com.example.tarefa.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class TarefaRequest {
    
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    
    private String descricao;
    
    @NotNull(message = "A data de entrega é obrigatória")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataEntrega;
    
    private boolean importante = false;
    
    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }
    
    public boolean isImportante() { return importante; }
    public void setImportante(boolean importante) { this.importante = importante; }
}
