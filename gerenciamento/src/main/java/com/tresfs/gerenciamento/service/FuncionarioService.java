package com.tresfs.gerenciamento.service;

import com.tresfs.gerenciamento.dto.FuncionarioDTO;
import com.tresfs.gerenciamento.dto.FuncionarioResponseDTO;
import com.tresfs.gerenciamento.entity.Funcionario;
import com.tresfs.gerenciamento.entity.Setor;
import com.tresfs.gerenciamento.entity.TipoUsuario;
import com.tresfs.gerenciamento.repository.FuncionarioRepository;
import com.tresfs.gerenciamento.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private SetorRepository setorRepository;

    public List<FuncionarioResponseDTO> listarTodos() {

        return repository.findAll().stream().map(f ->
        {
            FuncionarioResponseDTO dto = new FuncionarioResponseDTO();
            dto.setId(f.getId());
            dto.setNome(f.getNome());
            dto.setEmail(f.getEmail());
            dto.setTipoUsuario(f.getTipoUsuario());
            dto.setSetorNome(f.getSetor().getNome());
            dto.setDataCriacao(f.getDataCriacao());
            return dto;
        }).toList();
    }

    public FuncionarioResponseDTO salvar(FuncionarioDTO dto) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setEmail(dto.getEmail());
        funcionario.setSenha(dto.getSenha());
        funcionario.setTipoUsuario(dto.getTipoUsuario());

        Setor setor = setorRepository.
                findById(dto.getSetorId()).orElseThrow(() -> new RuntimeException("Setor não encontrado"));
        funcionario.setSetor(setor);

        Funcionario salvo = repository.save(funcionario);

        FuncionarioResponseDTO response = new FuncionarioResponseDTO();
        response.setId(salvo.getId());
        response.setNome(salvo.getNome());
        response.setEmail(salvo.getEmail());
        response.setTipoUsuario(salvo.getTipoUsuario());
        response.setSetorNome(salvo.getSetor().getNome());
        response.setDataCriacao(salvo.getDataCriacao());
        return response;
    }
}
