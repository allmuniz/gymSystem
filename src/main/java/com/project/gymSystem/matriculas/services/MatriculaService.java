package com.project.gymSystem.matriculas.services;

import com.project.gymSystem.matriculas.dtos.MatriculaRequestPagamento;
import com.project.gymSystem.matriculas.dtos.MatriculaResponsePagamento;
import com.project.gymSystem.matriculas.dtos.MatriculaResponseStatus;
import com.project.gymSystem.matriculas.entities.Matricula;
import com.project.gymSystem.matriculas.enuns.StatusDaMatricula;
import com.project.gymSystem.matriculas.repositories.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;

    public MatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }

    public MatriculaResponsePagamento pagarMatricula(UUID matriculaId, MatriculaRequestPagamento data) {
        Matricula matriculaFind = this.matriculaRepository.findById(matriculaId).orElse(null);
        if (matriculaFind != null) {
            matriculaFind.setDataPagamento(LocalDate.parse(data.dataPagamento(), DateTimeFormatter.ISO_LOCAL_DATE));
            matriculaFind.setDataVencimento(LocalDate.parse(data.dataPagamento(), DateTimeFormatter.ISO_LOCAL_DATE).plusDays(30));
            this.matriculaRepository.save(matriculaFind);
            return new MatriculaResponsePagamento(matriculaFind.getDataPagamento(), matriculaFind.getDataVencimento());
        }
        return null;
    }

    public MatriculaResponseStatus atualizaStatusMatricula(UUID matriculaId) {
        Matricula matriculaFind = this.matriculaRepository.findById(matriculaId).orElse(null);
        LocalDate dataAtual = LocalDate.now();
        int comparaData = dataAtual.compareTo(matriculaFind.getDataVencimento());

        if (comparaData < 5){
            matriculaFind.setStatus(StatusDaMatricula.ATIVA);
        } else if (comparaData <= 15) {
            matriculaFind.setStatus(StatusDaMatricula.EXPIRADA);
        } else {
            matriculaFind.setStatus(StatusDaMatricula.CANCELADA);
        }
        this.matriculaRepository.save(matriculaFind);
        return new MatriculaResponseStatus(matriculaFind.getStatus());
    }

    public void deletaMatricula(UUID matriculaId) {
        this.matriculaRepository.deleteById(matriculaId);
    }
}
