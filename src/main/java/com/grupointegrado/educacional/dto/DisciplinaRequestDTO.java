package com.grupointegrado.educacional.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DisciplinaRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O código é obrigatório")
        String codigo,

        @NotNull(message = "Curso é obrigatório")
        Integer cursoId,

        @NotNull(message = "Professor é obrigatório")
        Integer professorId
){}


