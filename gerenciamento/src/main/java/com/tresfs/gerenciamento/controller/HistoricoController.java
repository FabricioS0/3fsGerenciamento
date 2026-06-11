package com.tresfs.gerenciamento.controller;

import com.tresfs.gerenciamento.dto.HistoricoResponseDTO;
import com.tresfs.gerenciamento.entity.Historico;
import com.tresfs.gerenciamento.service.HistoricoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/historico")
public class HistoricoController {

    @Autowired
    private HistoricoService service;

    @GetMapping("/{documentoId}")
    public List<HistoricoResponseDTO> listar(@PathVariable Long documentoId) {
        return service.listarPorDocumento(documentoId);
    }
}
