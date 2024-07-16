package com.project.gymSystem.usuarios.controllers;

import com.project.gymSystem.usuarios.dtos.UsuarioRequest;
import com.project.gymSystem.usuarios.dtos.UsuarioRequestUpdate;
import com.project.gymSystem.usuarios.dtos.UsuarioResponseCreate;
import com.project.gymSystem.usuarios.dtos.UsuarioResponseFind;
import com.project.gymSystem.usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Gerenciamento dos usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/")
    @Operation(description = "Insira os dados para criar um usuário",
            summary = "Criação de Usuário")
    public ResponseEntity<UsuarioResponseCreate> createInstrutor(UsuarioRequest usuario) {
        UsuarioResponseCreate newUsuario = this.usuarioService.createUsuario(usuario);
        return ResponseEntity.ok().body(newUsuario);
    }

    @PutMapping("/{usuarioId}")
    @Operation(description = "Insira os dados para atualizar um usuário",
            summary = "Atualização de Usuário")
    public ResponseEntity<UsuarioResponseCreate> updateUsuario(@PathVariable UUID usuarioId, UsuarioRequestUpdate usuario) {
        UsuarioResponseCreate updUsuario = this.usuarioService.updateUsuario(usuarioId, usuario);
        return ResponseEntity.ok().body(updUsuario);
    }

    @GetMapping("/{usuarioId}")
    @Operation(description = "Insira o id para buscar um usuário",
            summary = "Pesquisa de Usuário")
    public UsuarioResponseFind findUsuario(@PathVariable UUID usuarioId) {
        return this.usuarioService.findUsuario(usuarioId);
    }
}