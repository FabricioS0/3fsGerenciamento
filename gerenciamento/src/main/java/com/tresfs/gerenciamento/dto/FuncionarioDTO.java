package com.tresfs.gerenciamento.dto;

import com.tresfs.gerenciamento.entity.TipoUsuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FuncionarioDTO {

    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipoUsuario;
    private Long setorId;
}