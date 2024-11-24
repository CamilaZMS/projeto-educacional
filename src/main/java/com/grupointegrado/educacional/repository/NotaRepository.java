package com.grupointegrado.educacional.repository;

import com.grupointegrado.educacional.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Integer> {
}
