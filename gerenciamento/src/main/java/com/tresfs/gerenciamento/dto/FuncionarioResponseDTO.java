package com.tresfs.gerenciamento.dto;

import com.tresfs.gerenciamento.entity.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class FuncionarioResponseDTO {
    private Long id;
    private String nome;
    private String email;
    private TipoUsuario tipoUsuario;
    private String setorNome;
    private LocalDateTime dataCriacao;
}
