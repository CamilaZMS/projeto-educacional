package com.grupointegrado.educacional.repository;

import com.grupointegrado.educacional.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
