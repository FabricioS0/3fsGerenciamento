package com.tresfs.gerenciamento.controller;

import com.tresfs.gerenciamento.config.TokenConfig;
import com.tresfs.gerenciamento.dto.Request.LoginRequest;
import com.tresfs.gerenciamento.dto.Request.RegisterRequest;
import com.tresfs.gerenciamento.dto.Response.LoginResponse;
import com.tresfs.gerenciamento.dto.Response.RegisterUserResponse;
import com.tresfs.gerenciamento.entity.Funcionario;
import com.tresfs.gerenciamento.entity.Setor;
import com.tresfs.gerenciamento.repository.FuncionarioRepository;
import com.tresfs.gerenciamento.repository.SetorRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthController(FuncionarioRepository funcionarioRepository, SetorRepository setorRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenConfig tokenConfig){
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> Login(@Valid @RequestBody LoginRequest request) {
        UsernamePasswordAuthenticationToken nomeESenha =
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.senha()
                );
        Authentication authentication = authenticationManager.authenticate(nomeESenha);

        Funcionario funcionario = (Funcionario) authentication.getPrincipal();
        String token = tokenConfig.generateToken(funcionario);

        return ResponseEntity.ok(new LoginResponse(token));
    }
    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterRequest request){
        Setor setor = setorRepository.findById(request.setorId())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        Funcionario funcionario = new Funcionario();
        funcionario.setSenha(passwordEncoder.encode(request.senha()));
        funcionario.setEmail(request.email());
        funcionario.setNome(request.nome());
        funcionario.setTipoUsuario(request.tipoUsuario());
        funcionario.setSetor(setor);

        funcionarioRepository.save(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterUserResponse(funcionario.getNome(), funcionario.getEmail()));
    }
}
