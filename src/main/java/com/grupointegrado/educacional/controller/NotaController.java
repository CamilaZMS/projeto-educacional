package com.grupointegrado.educacional.controller;

import com.grupointegrado.educacional.dto.NotaRequestDTO;
import com.grupointegrado.educacional.model.*;
import com.grupointegrado.educacional.repository.MatriculaRepository;

import com.grupointegrado.educacional.repository.NotaRepository;
import com.grupointegrado.educacional.repository.DisciplinaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/notas")
@Validated
public class NotaController {

    @Autowired
    private NotaRepository repository;

    @Autowired
    private MatriculaRepository matriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @GetMapping
    public ResponseEntity<List<Nota>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @PostMapping
    public Nota save(@Valid @RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();

        Matricula matricula = matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        nota.setMatricula(matricula);

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setDisciplina(disciplina);

        nota.setNota(dto.nota());

        nota.setDataLancamento(LocalDate.now());

        return this.repository.save(nota);
    }

    @PutMapping("/{id}")
    public Nota update(@PathVariable Integer id,
                            @RequestBody NotaRequestDTO dto) {
        Nota nota = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nota não encontrada"));

        Matricula matricula = matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        nota.setMatricula(matricula);

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setNota(dto.nota());

        nota.setDataLancamento(LocalDate.now());

        return this.repository.save(nota);
    }
}


