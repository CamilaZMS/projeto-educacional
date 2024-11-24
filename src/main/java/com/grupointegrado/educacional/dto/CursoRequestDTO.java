package com.grupointegrado.educacional.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O código é obrigatório")
        String codigo,

        @NotNull(message = "A carga horária é obrigatória")
        @Min(value = 1, message = "A carga horária deve ser maior que 0")
        Integer cargaHoraria
) {}
