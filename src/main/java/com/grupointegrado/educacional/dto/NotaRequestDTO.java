package com.grupointegrado.educacional.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record NotaRequestDTO (
        @NotNull(message = "Nota é obrigatória")
        @DecimalMin(value = "0.0", message = "Nota deve ser maior ou igual a 0")
        @DecimalMax(value = "10.0", message = "Nota deve ser menor ou igual a 10")
        BigDecimal nota,

        @NotNull(message = "Matricula é obrigatório")
        Integer matriculaId,

        @NotNull(message = "Disciplina é obrigatório")
        Integer disciplinaId
 ) {
}
