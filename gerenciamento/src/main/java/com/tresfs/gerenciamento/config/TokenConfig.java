package com.tresfs.gerenciamento.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.tresfs.gerenciamento.entity.Funcionario;

import java.time.Instant;


public class TokenConfig {

    private final String secret = "secret";

    public String generateToken(Funcionario funcionario) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("funcionarioId", funcionario.getId())
                .withSubject(funcionario.getEmail())
                .withIssuedAt(Instant.now())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .sign(algorithm);
    }
}