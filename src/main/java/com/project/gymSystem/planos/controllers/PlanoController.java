package com.project.gymSystem.planos.controllers;

import com.project.gymSystem.planos.dtos.PlanoRequest;
import com.project.gymSystem.planos.dtos.PlanoRequestUpdate;
import com.project.gymSystem.planos.dtos.PlanoResponseCreate;
import com.project.gymSystem.planos.dtos.PlanoResponseFind;
import com.project.gymSystem.planos.services.PlanoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/planos")
@Tag(name = "Planos", description = "Gerenciamento dos planos")
public class PlanoController {

    private final PlanoService planoService;

    public PlanoController(PlanoService planoService) {
        this.planoService = planoService;
    }
    @PostMapping("/")
    @Operation(description = "Insira os dados para criar um plano",
            summary = "Criação de Plano")
    public ResponseEntity<PlanoResponseCreate> createPlano(PlanoRequest plano) {
        PlanoResponseCreate newPlano = this.planoService.registerPlano(plano);
        return ResponseEntity.ok().body(newPlano);
    }

    @PutMapping("/{planoId}")
    @Operation(description = "Insira os dados para atualizar um plano",
            summary = "Atualização de planos")
    public PlanoResponseCreate updatePlano(@PathVariable UUID planoId, PlanoRequestUpdate plano) {
        return this.planoService.updatePlano(planoId, plano);
    }

    @GetMapping("/{planoId}")
    @Operation(description = "Insira o id para buscar um plano",
            summary = "Pesquisa de planos")
    public PlanoResponseFind findPlanoById(@PathVariable UUID planoId) {
        return this.planoService.findPlano(planoId);
    }

    @DeleteMapping("/{planoId}")
    @Operation(description = "Insira o id para deletar um plano",
            summary = "Remoção de plano")
    public void deletePlano(@PathVariable UUID planoId) {
        this.planoService.deletePlano(planoId);
    }
}
