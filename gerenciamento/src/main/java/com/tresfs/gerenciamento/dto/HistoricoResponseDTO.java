package com.tresfs.gerenciamento.dto;

import com.tresfs.gerenciamento.entity.Historico;
import com.tresfs.gerenciamento.entity.StatusAcao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

    @Getter
    @Setter
    public class HistoricoResponseDTO {
        private Long id;
        private StatusAcao acao;
        private String descricao;
        private LocalDateTime data;
        private String usuarioNome;

        public HistoricoResponseDTO(Historico h) {
            this.id = h.getId();
            this.acao = h.getAcao();
            this.descricao = h.getDescricao();
            this.data = h.getData();
            this.usuarioNome = h.getFuncionario().getNome();
        }
    }

