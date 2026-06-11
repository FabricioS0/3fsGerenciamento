package com.tresfs.gerenciamento.repository;

import com.tresfs.gerenciamento.entity.Documento;
import com.tresfs.gerenciamento.entity.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}
