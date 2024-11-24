package com.grupointegrado.educacional.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TurmaRequestDTO(
        @NotNull(message = "Ano é obrigatório")
        @Min(value = 1, message = "Ano deve ser maior que 0")
        Integer ano,

        @NotNull(message = "Semestre é obrigatório")
        @Min(value = 1, message = "Semestre deve ser 1 ou 2")
        @Max(value = 2, message = "Semestre deve ser 1 ou 2")
        Integer semestre,

        @NotNull(message = "Curso é obrigatório")
        Integer cursoId

) {}