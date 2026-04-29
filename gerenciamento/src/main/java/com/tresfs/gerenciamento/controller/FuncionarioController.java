package com.tresfs.gerenciamento.controller;

import com.tresfs.gerenciamento.dto.FuncionarioDTO;
import com.tresfs.gerenciamento.dto.FuncionarioResponseDTO;
import com.tresfs.gerenciamento.entity.Funcionario;
import com.tresfs.gerenciamento.service.FuncionarioService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;
    @GetMapping
    public List<FuncionarioResponseDTO> listar(){
        return service.listarTodos();
    }

    @PostMapping
    public FuncionarioResponseDTO salvar(@RequestBody FuncionarioDTO dto){
        return service.salvar(dto);
    }
}
