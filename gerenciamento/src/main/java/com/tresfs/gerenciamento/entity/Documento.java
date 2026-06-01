package com.tresfs.gerenciamento.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Documento {
    @Id
    private Long id;

    private String titulo;

    private String descricao;

    private String tipo;

    private String numero;

    @Enumerated(EnumType.STRING)
    private StatusDocumento status;

    private LocalDateTime dataCadastro;

    @ManyToOne
    @JoinColumn(name = "setor_id", nullable = false)
    private Setor setor;

    @ManyToOne
    @JoinColumn(name = "usuario_cadastro_id", nullable = false)
    private Funcionario usuarioCadastro;
    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDateTime.now();
        if (this.status == null) this.status = StatusDocumento.ATIVO;
    }

    @OneToMany(mappedBy = "documento")
    private List<Historico> historicos = new ArrayList<>();
}
