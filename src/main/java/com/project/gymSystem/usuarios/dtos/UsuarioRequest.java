package com.project.gymSystem.usuarios.dtos;

import com.project.gymSystem.planos.entities.Plano;
import com.project.gymSystem.usuarios.enuns.TipoDeUsuarioEnum;

import java.util.UUID;

public record UsuarioRequest(
        String cpf,
        String nome,
        String email,
        String senha,
        String telefone,
        String endereco,
        TipoDeUsuarioEnum tipo,
        UUID planoId) {
}
