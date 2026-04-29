package com.tresfs.gerenciamento.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    @Enumerated
    private TipoUsuario tipoUsuario;
    private LocalDateTime dataCriacao;
    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    private Setor setor;
    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
    }

}
