package com.tresfs.gerenciamento.repository;

import com.tresfs.gerenciamento.entity.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
}
