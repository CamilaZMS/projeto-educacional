package com.grupointegrado.educacional.controller;

import com.grupointegrado.educacional.dto.AlunoRequestDTO;
import com.grupointegrado.educacional.model.Aluno;
import com.grupointegrado.educacional.repository.AlunoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/alunos")
@Validated
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @GetMapping
    public ResponseEntity<List<Aluno>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public Aluno save(@Valid @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setDataNascimento(dto.dataNascimento());

        if (dto.email() != null && !dto.email().isBlank()) {
            aluno.setEmail(dto.email());
        } else {
            aluno.setEmail(null);
        }

        return  this.repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id,
                        @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        if (!Objects.equals(aluno.getNome(), dto.nome())) {
            aluno.setNome(dto.nome());
        }

        if (!Objects.equals(aluno.getEmail(), dto.email())) {
            aluno.setEmail(dto.email());
        }

        if (!Objects.equals(aluno.getDataNascimento(), dto.dataNascimento())) {
            aluno.setDataNascimento(dto.dataNascimento());
        }

        return this.repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
}


