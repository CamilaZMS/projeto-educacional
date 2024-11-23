package com.grupointegrado.educacional.controller;


import com.grupointegrado.educacional.dto.MatriculaRequestDTO;
import com.grupointegrado.educacional.model.Aluno;
import com.grupointegrado.educacional.model.Matricula;
import com.grupointegrado.educacional.model.Turma;
import com.grupointegrado.educacional.repository.AlunoRepository;
import com.grupointegrado.educacional.repository.MatriculaRepository;
import com.grupointegrado.educacional.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public ResponseEntity<List<Matricula>> findAll() {
        return ResponseEntity.ok(this.repository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matricula> findById(@PathVariable Integer id) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        return ResponseEntity.ok(matricula);
    }

    @PostMapping
    public Matricula save(@RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = new Matricula();

        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        matricula.setAluno(aluno);

        Turma turma = turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        matricula.setTurma(turma);

        return this.repository.save(matricula);
    }

    @PutMapping("/{id}")
    public Matricula update(@PathVariable Integer id,
                        @RequestBody MatriculaRequestDTO dto) {
        Matricula matricula = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Matricula não encontrada"));

        Turma turma = turmaRepository.findById(dto.turmaId())
                .orElseThrow(() -> new IllegalArgumentException("Turma não encontrada"));

        matricula.setTurma(turma);

        Aluno aluno = alunoRepository.findById(dto.alunoId())
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        matricula.setAluno(aluno);

        return this.repository.save(matricula);
    }

}


