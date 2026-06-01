package com.tresfs.gerenciamento.service;

import com.tresfs.gerenciamento.dto.DocumentoDTO;
import com.tresfs.gerenciamento.dto.DocumentoResponseDTO;
import com.tresfs.gerenciamento.entity.Documento;
import com.tresfs.gerenciamento.entity.Funcionario;
import com.tresfs.gerenciamento.entity.Setor;
import com.tresfs.gerenciamento.entity.StatusAcao;
import com.tresfs.gerenciamento.repository.DocumentoRepository;
import com.tresfs.gerenciamento.repository.FuncionarioRepository;
import com.tresfs.gerenciamento.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoService {
    @Autowired
    private DocumentoRepository repository;
    @Autowired
    private SetorRepository setorRepository;
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private HistoricoService historicoService;

    public List<DocumentoResponseDTO> listarTodos(){
        return repository.findAll().stream().map(DocumentoResponseDTO::new).toList();
    }

    public DocumentoResponseDTO salvar(DocumentoDTO dto) {

        Documento documento = new Documento();
        documento.setId(dto.getId());
        documento.setTitulo(dto.getTitulo());
        documento.setDescricao(dto.getDescricao());
        documento.setTipo(dto.getTipo());
        documento.setNumero(dto.getNumero());

        Setor setor = setorRepository.findById(dto.getSetorId())
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));
        documento.setSetor(setor);

        Funcionario funcionario = funcionarioRepository.findById(dto.getUsuarioCadastroId())
                .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        documento.setUsuarioCadastro(funcionario);

        Documento salvo = repository.save(documento);

        historicoService.registrar(
                salvo,
                funcionario,
                StatusAcao.CADASTRO,
                "Documento cadastrado no sistema"
        );

        return new DocumentoResponseDTO(salvo);
    }

    public DocumentoResponseDTO atualizar(Long id, DocumentoDTO dto) {
        Documento documento = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento com o id inexistente"));

        documento.setTitulo(dto.getTitulo());
        documento.setDescricao(dto.getDescricao());
        documento.setTipo(dto.getTipo());

        Documento atualizado = repository.save(documento);

        Funcionario funcionario = funcionarioRepository
                .findById(dto.getUsuarioCadastroId())
                .orElseThrow(() -> new RuntimeException("Funcionario com o id inexistente"));

        historicoService.registrar(
                atualizado,
                funcionario,
                StatusAcao.EDICAO,
                "Documento atualizado no sistema"
        );
        return new DocumentoResponseDTO(atualizado);
    }

    public void moverDocumento(Long documentoId, Long setorId, Long funcionarioId) {
        Documento documento = repository.findById(documentoId)
                .orElseThrow(() -> new RuntimeException("Documento com o id inexistente"));

        Setor novoSetor = setorRepository.findById(setorId)
                .orElseThrow(() -> new RuntimeException("Setor não encontrado"));

        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionario com o id inexistente"));

        String setorAnterior = documento.getSetor().getNome();

        documento.setSetor(novoSetor);
        repository.save(documento);
        historicoService.registrar(
                documento,
                funcionario,
                StatusAcao.MOVIMENTACAO,
                "Documento movido de " + setorAnterior + " para " + novoSetor.getNome()
        );
    }
}
