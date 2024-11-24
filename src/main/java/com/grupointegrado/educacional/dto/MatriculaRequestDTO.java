package com.grupointegrado.educacional.dto;

import jakarta.validation.constraints.NotNull;

public record MatriculaRequestDTO(
        @NotNull(message = "Aluno é obrigatório")
        Integer alunoId,

        @NotNull(message = "Truma é obrigatório")
        Integer turmaId
) {
}
