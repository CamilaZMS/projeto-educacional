package com.grupointegrado.educacional.controller;


import com.grupointegrado.educacional.dto.ProfessorRequestDTO;
import com.grupointegrado.educacional.model.Professor;
import com.grupointegrado.educacional.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    public ResponseEntity<List<Professor>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public Professor save(@RequestBody ProfessorRequestDTO dto) {
            Professor professor = new Professor();
            professor.setNome(dto.nome());
            professor.setEmail(dto.email());
            professor.setTelefone(dto.telefone());
            professor.setEspecialidade(dto.especialidade());
            return this.repository.save(professor);
    }

    @PutMapping("/{id}")
    public Professor update(@PathVariable Integer id,
                        @RequestBody ProfessorRequestDTO dto) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        if (!professor.getNome().equals(dto.nome())) {
            professor.setNome(dto.nome());
        }

        if (!professor.getEmail().equals(dto.email())) {
            professor.setEmail(dto.email());
        }

        if (!professor.getTelefone().equals(dto.telefone())) {
            professor.setTelefone(dto.telefone());
        }

        if (!professor.getEspecialidade().equals(dto.especialidade())) {
            professor.setEspecialidade(dto.especialidade());
        }

        return this.repository.save(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Professor professor = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));
        this.repository.delete(professor);
        return ResponseEntity.noContent().build();
    }
}


