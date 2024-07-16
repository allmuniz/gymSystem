package com.project.gymSystem.usuarios.dtos;

public record UsuarioRequestUpdate(
        String nome,
        String email,
        String senha,
        String telefone,
        String endereco)  {
}
