package com.project.gymSystem.aulas.dtos;

import java.time.LocalDateTime;

public record AulaResponseFindAluno(
        String titulo,
        LocalDateTime inicioAula,
        LocalDateTime fimAula) {
}
