package com.grupointegrado.educacional.repository;

import com.grupointegrado.educacional.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {
}