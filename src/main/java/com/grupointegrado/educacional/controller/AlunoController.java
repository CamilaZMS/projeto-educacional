package com.grupointegrado.educacional.controller;

import com.grupointegrado.educacional.dto.AlunoRequestDTO;
import com.grupointegrado.educacional.model.Aluno;
import com.grupointegrado.educacional.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
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
    public Aluno save(@RequestBody AlunoRequestDTO dto) {
            Aluno aluno = new Aluno();
            aluno.setNome(dto.nome());
            aluno.setEmail(dto.email());
            aluno.setDataNascimento(dto.dataNascimento());
//            TO DO gerar matricula automatica
            aluno.setMatricula(dto.matricula());
            return  this.repository.save(aluno);
    }

    @PutMapping("/{id}")
    public Aluno update(@PathVariable Integer id,
                        @RequestBody AlunoRequestDTO dto) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));

        if (!aluno.getNome().equals(dto.nome())) {
            aluno.setNome(dto.nome());
        }

        if (!aluno.getEmail().equals(dto.email())) {
            aluno.setEmail(dto.email());
        }

        if (!aluno.getDataNascimento().equals(dto.dataNascimento())) {
            aluno.setDataNascimento(dto.dataNascimento());
        }

        if (!aluno.getMatricula().equals(dto.matricula())) {
            aluno.setMatricula(dto.matricula());
        }

        return  this.repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        Aluno aluno = this.repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado"));
        this.repository.delete(aluno);
        return ResponseEntity.noContent().build();
    }
}


