package com.project.gymSystem.usuarios.entities;

import com.project.gymSystem.aulas.entities.Aula;
import com.project.gymSystem.usuarios.enuns.TipoDeUsuarioEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String cpf;

    private String nome;

    private String email;

    private String senha;

    private String telefone;

    private String endereco;

    @Enumerated(EnumType.STRING)
    private TipoDeUsuarioEnum tipo;

    @ManyToMany(mappedBy = "alunos")
    private List<Aula> aulas;

    public Usuario(String cpf, String nome, String email, String senha, String telefone, String endereco, TipoDeUsuarioEnum tipo) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
        this.tipo = tipo;
    }
}
