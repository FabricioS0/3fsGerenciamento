package com.tresfs.gerenciamento.repository;

import com.tresfs.gerenciamento.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
    List<Historico> findByDocumentoId(Long documentoId);
}
