package com.grupointegrado.educacional.controller;


import com.grupointegrado.educacional.dto.TurmaRequestDTO;
import com.grupointegrado.educacional.model.Curso;
import com.grupointegrado.educacional.model.Turma;
import com.grupointegrado.educacional.repository.CursoRepository;
import com.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping
    public ResponseEntity<List<Turma>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> findById(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        return ResponseEntity.ok(turma);
    }

    @PostMapping
    public Turma save(@RequestBody TurmaRequestDTO dto) {
        Turma turma = new Turma();
        turma.setAno(dto.ano());
        turma.setSemestre(dto.semestre());

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        turma.setCurso(curso);
        return this.repository.save(turma);
    }

    @PutMapping("/{id}")
    public Turma update(@PathVariable Integer id,
                        @RequestBody TurmaRequestDTO dto) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));

        if (!turma.getAno().equals(dto.ano())) {
            turma.setAno(dto.ano());
        }

        if (!turma.getSemestre().equals(dto.semestre())) {
            turma.setSemestre(dto.semestre());
        }

        Curso curso = cursoRepository.findById(dto.cursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        turma.setCurso(curso);

        return this.repository.save(turma);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Turma turma = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrado"));
        this.repository.delete(turma);
        return ResponseEntity.noContent().build();
    }
}


