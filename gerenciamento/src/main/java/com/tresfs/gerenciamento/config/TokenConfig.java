package com.tresfs.gerenciamento.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.tresfs.gerenciamento.entity.Funcionario;
import com.tresfs.gerenciamento.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class TokenConfig {

    @Value("${security.token.jwt.secret")
    private String secret;

    @Value("${security.token.jwt.expiration")
    private Long exp;

    private final FuncionarioRepository funcionarioRepository;

    public String generateToken(Funcionario funcionario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api-jwt")
                    .withSubject(funcionario.getEmail())
                    .withExpiresAt(genarateExpirationTime())
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            System.out.println("JWT generation failed");
            return null;
        }
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api-jwt")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println("Invalid JWT token");
            return null;
        }

    }

    private Instant genarateExpirationTime() {
        return LocalDateTime.now().plusHours(exp).toInstant(ZoneOffset.of("-03:00"));
    }

    public Funcionario getUserByToken(String token) {
        token = token.substring(7).trim();
        String id = JWT.decode(token).getSubject();
        return funcionarioRepository.findByEmail(id).orElse(null);
    }
}