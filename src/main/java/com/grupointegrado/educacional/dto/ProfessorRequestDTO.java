package com.grupointegrado.educacional.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ProfessorRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos")
        String telefone,

        @NotBlank(message = "A especialidade é obrigatória")
        String especialidade
) {}