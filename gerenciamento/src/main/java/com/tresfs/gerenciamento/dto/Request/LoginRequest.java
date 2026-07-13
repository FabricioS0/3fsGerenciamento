package com.tresfs.gerenciamento.dto.Request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message="Email é obrigatorio") String email,
                           @NotEmpty(message = "Senha é obtrigatório") String senha) {
}
