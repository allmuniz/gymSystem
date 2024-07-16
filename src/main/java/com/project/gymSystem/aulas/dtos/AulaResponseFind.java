package com.project.gymSystem.aulas.dtos;

import com.project.gymSystem.usuarios.dtos.UsuarioResponseFindAulas;

import java.time.LocalDateTime;
import java.util.List;

public record AulaResponseFind(
        String titulo,
        LocalDateTime inicioAula,
        LocalDateTime fimAula,
        List<UsuarioResponseFindAulas> alunos) {
}
