package com.tresfs.gerenciamento.repository;

import com.tresfs.gerenciamento.entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
