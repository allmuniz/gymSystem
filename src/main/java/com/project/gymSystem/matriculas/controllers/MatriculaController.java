package com.project.gymSystem.matriculas.controllers;

import com.project.gymSystem.matriculas.dtos.MatriculaRequestPagamento;
import com.project.gymSystem.matriculas.dtos.MatriculaResponsePagamento;
import com.project.gymSystem.matriculas.dtos.MatriculaResponseStatus;
import com.project.gymSystem.matriculas.services.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/matriculas")
@Tag(name = "Matriculas", description = "Gerenciamento das matriculas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @PostMapping("/{matriculaId}/pagamento")
    @Operation(description = "Insira os dados para cadastrar pagamento",
            summary = "Cadastro de pagamento")
    public MatriculaResponsePagamento pagaMatricula(@PathVariable UUID matriculaId, MatriculaRequestPagamento data){
        return this.matriculaService.pagarMatricula(matriculaId, data);
    }

    @GetMapping("/{matriculaId}/consulta")
    @Operation(description = "Insira o id para verificar a matricula",
            summary = "Consulta do status da matricula")
    public MatriculaResponseStatus consultaStatusMatricula(@PathVariable UUID matriculaId){
        return this.matriculaService.atualizaStatusMatricula(matriculaId);
    }

    @DeleteMapping("/{matriculaId}")
    @Operation(description = "Insira o id para deletar uma matricula",
            summary = "Remoção de matricula")
    public void deleteMatricula(@PathVariable UUID matriculaId){
        this.matriculaService.deletaMatricula(matriculaId);
    }
}
