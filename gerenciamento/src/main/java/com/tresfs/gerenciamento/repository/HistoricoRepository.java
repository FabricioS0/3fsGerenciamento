package com.tresfs.gerenciamento.repository;

import com.tresfs.gerenciamento.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
}
