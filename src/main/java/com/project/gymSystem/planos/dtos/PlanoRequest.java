package com.project.gymSystem.planos.dtos;

import com.project.gymSystem.planos.enuns.TipoDePlanoEnum;

public record PlanoRequest(
        TipoDePlanoEnum tipo,
        String beneficios,
        double preco) {
}
