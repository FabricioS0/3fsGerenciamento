package com.tresfs.gerenciamento.controller;

import com.tresfs.gerenciamento.dto.DocumentoDTO;
import com.tresfs.gerenciamento.dto.DocumentoResponseDTO;
import com.tresfs.gerenciamento.repository.DocumentoRepository;
import com.tresfs.gerenciamento.service.DocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documentos")
public class DocumentoController {
    @Autowired
    private DocumentoService service;

    @GetMapping
    public List<DocumentoResponseDTO> listar() {return service.listarTodos();}

    @PostMapping
    public DocumentoResponseDTO salvar(@RequestBody DocumentoDTO dto){ return service.salvar(dto);}

}
