package com.project.gymSystem.aulas.services;

import com.project.gymSystem.aulas.dtos.AulaRequest;
import com.project.gymSystem.aulas.dtos.AulaResponseCreate;
import com.project.gymSystem.aulas.dtos.AulaResponseFind;
import com.project.gymSystem.aulas.entities.Aula;
import com.project.gymSystem.aulas.repositories.AulaRepository;
import com.project.gymSystem.usuarios.dtos.UsuarioResponseFindAulas;
import com.project.gymSystem.usuarios.entities.Usuario;
import com.project.gymSystem.usuarios.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final UsuarioRepository usuarioRepository;


    public AulaService(AulaRepository aulaRepository, UsuarioRepository usuarioRepository) {
        this.aulaRepository = aulaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public AulaResponseCreate createAula(AulaRequest aula) {
        Aula newAula = new Aula(aula);

        aulaRepository.save(newAula);
        return new AulaResponseCreate(newAula.getId());
    }

    public AulaResponseCreate definirInstrutor(UUID aulaId, UUID instrutorId) {
        Aula aula = aulaRepository.findById(aulaId).orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
        Usuario instrutor = usuarioRepository.findById(instrutorId).orElseThrow(null);
        if (aula.getInstrutor() == null){
            aula.definirInstrutor(instrutor);
            aulaRepository.save(aula);
            return new AulaResponseCreate(aulaId);
        }
        throw new IllegalArgumentException("Aula já possui instrutor");
    }

    public AulaResponseCreate adicionarAluno(UUID aulaId, UUID alunoId) {
        Aula aula = aulaRepository.findById(aulaId).orElseThrow(() -> new IllegalArgumentException("Aula não encontrada"));
        Usuario aluno = usuarioRepository.findById(alunoId).orElseThrow(null);

        aula.adicionarAluno(aluno);
        aulaRepository.save(aula);
        return new AulaResponseCreate(aulaId);
    }

    public List<UsuarioResponseFindAulas> buscarAlunosPorAulaId(UUID aulaId) {
        Aula aula = aulaRepository.findById(aulaId).orElseThrow(() -> new RuntimeException("Aula não encontrada"));
        return aula.getAlunos().stream()
                .map(this::toUsuarioResponseFind)
                .collect(Collectors.toList());
    }

    public AulaResponseCreate updateAula(UUID aulaId, AulaRequest aulaRequest) {
        Aula aula = this.aulaRepository.findById(aulaId).orElse(null);
        if (aula != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

            aula.setTitulo(aulaRequest.titulo());
            aula.setInicioAula(LocalDateTime.parse(aulaRequest.inicioAula(), formatter));
            aula.setFimAula(LocalDateTime.parse(aulaRequest.fimAula(), formatter));
            aulaRepository.save(aula);
            return new AulaResponseCreate(aulaId);
        }
        return null;
    }

    public AulaResponseFind findAulas(UUID aulaId) {
        Aula aula = aulaRepository.findById(aulaId).orElse(null);
        if (aula != null) {
            return toAulaResponseFind(aula);
        }
        return null;
    }

    public void deleteAula(UUID aulaId){
        this.aulaRepository.deleteById(aulaId);
    }


    private AulaResponseFind toAulaResponseFind(Aula aula) {
        List<UsuarioResponseFindAulas> alunos = aula.getAlunos().stream()
                .map(this::toUsuarioResponseFind)
                .collect(Collectors.toList());
        return new AulaResponseFind(
                aula.getTitulo(),
                aula.getInicioAula(),
                aula.getFimAula(),
                alunos
        );
    }

    private UsuarioResponseFindAulas toUsuarioResponseFind(Usuario usuario) {
        return new UsuarioResponseFindAulas(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getEndereco()
        );
    }
}
