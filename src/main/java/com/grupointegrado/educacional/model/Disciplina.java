package com.grupointegrado.educacional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,  length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id",  nullable = false)
    @JsonIgnore
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "professor_id", referencedColumnName = "id",  nullable = false)
    @JsonIgnore
    private Professor professor;

    @OneToMany(mappedBy = "disciplina")
    @JsonIgnore
    private List<Nota> notas;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
}
