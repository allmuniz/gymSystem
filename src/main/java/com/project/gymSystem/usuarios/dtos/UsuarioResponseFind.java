package com.project.gymSystem.usuarios.dtos;

import com.project.gymSystem.aulas.dtos.AulaResponseFindAluno;

import java.util.List;

public record UsuarioResponseFind(
        String nome,
        String email,
        String telefone,
        String endereco,
        List<AulaResponseFindAluno> aulas) {
}
