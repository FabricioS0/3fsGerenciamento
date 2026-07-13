package com.tresfs.gerenciamento.dto.Request;

import com.tresfs.gerenciamento.entity.TipoUsuario;
import com.tresfs.gerenciamento.entity.Funcionario;
import jakarta.validation.constraints.NotEmpty;

public record RegisterRequest(@NotEmpty(message = "Nome é obrigatorio") String nome,
                              @NotEmpty(message="email é obrigatorio") String email,
                              @NotEmpty(message="Senha é obrigatorio") String senha,
                              @NotEmpty(message="Tipo de usuario é obrigatorio")TipoUsuario tipoUsuario,
                              @NotEmpty(message="Setor é obrigatorio") long setorId) {
}
