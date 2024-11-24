package com.grupointegrado.educacional.controller;


import com.grupointegrado.educacional.dto.DisciplinaRequestDTO;
import com.grupointegrado.educacional.model.Curso;
import com.grupointegrado.educacional.model.Disciplina;
import com.grupointegrado.educacional.model.Professor;
import com.grupointegrado.educacional.repository.CursoRepository;
import com.grupointegrado.educacional.repository.DisciplinaRepository;
import com.grupointegrado.educacional.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@Validated
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping
    public ResponseEntity<List<Disciplina>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public Disciplina save(@Valid @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        disciplina.setCurso(curso);

        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new IllegalArgumentException("Professor não encontrado"));

        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

    @PutMapping("/{id}")
    public Disciplina update(@PathVariable Integer id,
                        @RequestBody DisciplinaRequestDTO dto) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        if (!disciplina.getNome().equals(dto.nome())) {
            disciplina.setNome(dto.nome());
        }

        if (!disciplina.getCodigo().equals(dto.codigo())) {
            disciplina.setCodigo(dto.codigo());
        }

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        disciplina.setCurso(curso);

        Professor professor = professorRepository.findById(dto.professorId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        disciplina.setProfessor(professor);

        return this.repository.save(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Disciplina disciplina = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));
        this.repository.delete(disciplina);
        return ResponseEntity.noContent().build();
    }
}


