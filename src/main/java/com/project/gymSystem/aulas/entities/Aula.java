package com.project.gymSystem.aulas.entities;

import com.project.gymSystem.aulas.dtos.AulaRequest;
import com.project.gymSystem.usuarios.entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "aulas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String titulo;

    private LocalDateTime inicioAula;

    private LocalDateTime fimAula;

    @ManyToMany
    @JoinTable(
            name = "aula_alunos",
            joinColumns = @JoinColumn(name = "aula_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Usuario> alunos;

    @OneToOne
    @JoinColumn(name = "instrutor_id")
    private Usuario instrutor;

    public void adicionarAluno(Usuario aluno) {
        this.alunos.add(aluno);
    }

    public void definirInstrutor(Usuario instrutor) {
        this.instrutor = instrutor;
    }

    public Aula(AulaRequest aula) {
        this.titulo = aula.titulo();
        this.inicioAula = LocalDateTime.parse(aula.inicioAula(), DateTimeFormatter.ISO_DATE_TIME);
        this.fimAula = LocalDateTime.parse(aula.fimAula(), DateTimeFormatter.ISO_DATE_TIME);
    }
}
