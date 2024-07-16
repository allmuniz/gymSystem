package com.project.gymSystem.matriculas.dtos;

import java.time.LocalDate;

public record MatriculaResponsePagamento(
        LocalDate dataPagamento,
        LocalDate dataVencimento) {
}
