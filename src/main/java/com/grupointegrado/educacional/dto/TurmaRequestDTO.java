package com.grupointegrado.educacional.dto;

import com.grupointegrado.educacional.model.Curso;

public record TurmaRequestDTO(Integer ano, Integer semestre, Integer cursoId) {
}