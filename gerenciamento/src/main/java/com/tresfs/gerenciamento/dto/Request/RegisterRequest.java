package com.tresfs.gerenciamento.dto.Request;

import com.tresfs.gerenciamento.entity.TipoUsuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(@NotEmpty(message = "Nome é obrigatorio") String nome,
                              @NotEmpty(message="email é obrigatorio") String email,
                              @NotEmpty(message="Senha é obrigatorio") String senha,
                              @NotNull(message="Tipo de usuario é obrigatorio") TipoUsuario tipoUsuario,
                              @NotNull(message="Setor é obrigatorio") Long setorId) {
}
