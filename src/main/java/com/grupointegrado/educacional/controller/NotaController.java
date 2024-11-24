package com.grupointegrado.educacional.controller;

import com.grupointegrado.educacional.dto.NotaRequestDTO;
import com.grupointegrado.educacional.model.*;
import com.grupointegrado.educacional.repository.MatriculaRepository;

import com.grupointegrado.educacional.repository.NotaRepository;
import com.grupointegrado.educacional.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notas")
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
    public Nota save(@RequestBody NotaRequestDTO dto) {
        Nota nota = new Nota();

        Matricula matricula = matriculaRepository.findById(dto.matriculaId())
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        nota.setMatricula(matricula);

        Disciplina disciplina = disciplinaRepository.findById(dto.disciplinaId())
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada"));

        nota.setDisciplina(disciplina);

        return this.repository.save(nota);
    }
}


