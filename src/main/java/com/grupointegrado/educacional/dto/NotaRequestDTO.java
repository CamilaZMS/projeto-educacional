package com.grupointegrado.educacional.dto;

import java.math.BigDecimal;

public record NotaRequestDTO (BigDecimal nota, Integer matriculaId, Integer disciplinaId) {
}
