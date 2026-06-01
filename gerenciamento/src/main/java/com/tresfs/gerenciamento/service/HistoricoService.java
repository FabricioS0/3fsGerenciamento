package com.tresfs.gerenciamento.service;

import com.tresfs.gerenciamento.entity.Documento;
import com.tresfs.gerenciamento.entity.Funcionario;
import com.tresfs.gerenciamento.entity.Historico;
import com.tresfs.gerenciamento.entity.StatusAcao;
import com.tresfs.gerenciamento.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoricoService {
    @Autowired
    private HistoricoRepository historicoRepository;

    public void registrar(Documento documento, Funcionario funcionario, StatusAcao acao, String descricao) {
        Historico historico = new Historico();
        historico.setFuncionario(funcionario);
        historico.setDescricao(descricao);
        historico.setDocumento(documento);
        historico.setAcao(acao);

        historicoRepository.save(historico);
    }
}
