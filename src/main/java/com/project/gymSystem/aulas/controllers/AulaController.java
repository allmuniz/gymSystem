package com.project.gymSystem.aulas.controllers;

import com.project.gymSystem.aulas.dtos.AulaRequest;
import com.project.gymSystem.aulas.dtos.AulaRequestUsuario;
import com.project.gymSystem.aulas.dtos.AulaResponseCreate;
import com.project.gymSystem.aulas.dtos.AulaResponseFind;
import com.project.gymSystem.aulas.services.AulaService;
import com.project.gymSystem.usuarios.dtos.UsuarioResponseFindAulas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/aulas")
@Tag(name = "Aulas", description = "Gerenciamento das aulas")
public class AulaController {

    private final AulaService aulaService;

    public AulaController(AulaService aulaService) {
        this.aulaService = aulaService;
    }

    @PostMapping("/")
    @Operation(description = "Insira os dados para criar uma aula",
            summary = "Criação de Aula")
    public ResponseEntity<AulaResponseCreate> createAula(AulaRequest aula) {
        AulaResponseCreate newAula = aulaService.createAula(aula);
        return ResponseEntity.ok().body(newAula);
    }

    @PostMapping("/{aulaId}/instrutor")
    @Operation(description = "Insira os dados para adicionar instrutor",
            summary = "Adicionar instrutor a aula")
    public ResponseEntity<AulaResponseCreate> adicionaInstrutor(@PathVariable UUID aulaId, AulaRequestUsuario dados) {
        AulaResponseCreate newInstrutor = aulaService.definirInstrutor(aulaId, dados.usuarioId());
        return ResponseEntity.ok().body(newInstrutor);
    }

    @PostMapping("/{aulaId}/alunos")
    @Operation(description = "Insira os dados para adicionar alunos",
            summary = "Adicionar alunos a aula")
    public ResponseEntity<AulaResponseCreate> adicionaAluno(@PathVariable UUID aulaId, AulaRequestUsuario dados) {
        AulaResponseCreate newAluno = aulaService.adicionarAluno(aulaId, dados.usuarioId());
        return ResponseEntity.ok().body(newAluno);
    }

    @GetMapping("/{aulaId}/alunos")
    @Operation(description = "Insira o id para buscar os alunos",
            summary = "Buscar os alunos da aula")
    public List<UsuarioResponseFindAulas> buscarAlunosPorAula(@PathVariable UUID aulaId) {
        return aulaService.buscarAlunosPorAulaId(aulaId);
    }

    @PutMapping("/{aulaId}")
    @Operation(description = "Insira o id para atualizar uma aula",
            summary = "Atualiza uma aula")
    public ResponseEntity<AulaResponseCreate> updateAula(@PathVariable UUID aulaId, AulaRequest aula) {
        AulaResponseCreate aulaUpdate = aulaService.updateAula(aulaId, aula);
        return ResponseEntity.ok().body(aulaUpdate);
    }

    @GetMapping("/{aulaId}")
    @Operation(description = "Insira o id para buscar uma aula",
            summary = "Pesquisa de aula")
    public AulaResponseFind findAulas(@PathVariable UUID aulaId) {
        return this.aulaService.findAulas(aulaId);
    }

    @DeleteMapping("/{aulaId}")
    @Operation(description = "Insira o id para deletar uma aula",
            summary = "Remoção de aula")
    public void deleteAula(@PathVariable UUID aulaId) {
        aulaService.deleteAula(aulaId);
    }
}
