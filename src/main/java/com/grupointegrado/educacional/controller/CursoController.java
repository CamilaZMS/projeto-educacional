package com.grupointegrado.educacional.controller;

import com.grupointegrado.educacional.dto.CursoRequestDTO;
import com.grupointegrado.educacional.model.Curso;
import com.grupointegrado.educacional.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        return ResponseEntity.ok(curso);
    }

    @PostMapping
    public Curso save(@RequestBody CursoRequestDTO dto) {
            Curso curso = new Curso();
            curso.setNome(dto.nome());
            curso.setCodigo(dto.codigo());
            curso.setCargaHoraria(dto.cargaHoraria());
            return this.repository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso update(@PathVariable Integer id,
                        @RequestBody CursoRequestDTO dto) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));

        if (!curso.getNome().equals(dto.nome())) {
            curso.setNome(dto.nome());
        }

        if (!curso.getCodigo().equals(dto.codigo())) {
            curso.setCodigo(dto.codigo());
        }

        if (!curso.getCargaHoraria().equals(dto.cargaHoraria())) {
            curso.setCargaHoraria(dto.cargaHoraria());
        }

        return this.repository.save(curso);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Curso curso = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        this.repository.delete(curso);
        return ResponseEntity.noContent().build();
    }
}


