package com.grupointegrado.educacional.repository;

import com.grupointegrado.educacional.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
