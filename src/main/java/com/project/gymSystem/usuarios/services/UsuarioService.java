package com.project.gymSystem.usuarios.services;

import com.project.gymSystem.aulas.dtos.AulaResponseFindAluno;
import com.project.gymSystem.aulas.entities.Aula;
import com.project.gymSystem.exceptions.UsuarioFoundException;
import com.project.gymSystem.matriculas.entities.Matricula;
import com.project.gymSystem.matriculas.enuns.StatusDaMatricula;
import com.project.gymSystem.matriculas.repositories.MatriculaRepository;
import com.project.gymSystem.planos.entities.Plano;
import com.project.gymSystem.planos.repositories.PlanoRepository;
import com.project.gymSystem.usuarios.dtos.UsuarioRequest;
import com.project.gymSystem.usuarios.dtos.UsuarioRequestUpdate;
import com.project.gymSystem.usuarios.dtos.UsuarioResponseCreate;
import com.project.gymSystem.usuarios.dtos.UsuarioResponseFind;
import com.project.gymSystem.usuarios.entities.Usuario;
import com.project.gymSystem.usuarios.enuns.TipoDeUsuarioEnum;
import com.project.gymSystem.usuarios.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;
    private final MatriculaRepository matriculaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PlanoRepository planoRepository, MatriculaRepository matriculaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.planoRepository = planoRepository;
        this.matriculaRepository = matriculaRepository;
    }

    public UsuarioResponseCreate createUsuario(UsuarioRequest usuario) {
        UsuarioRequest usuarioVerificado = verificaExistencia(usuario);

        Usuario newUsuario = new Usuario(usuarioVerificado.cpf(), usuarioVerificado.nome(), usuarioVerificado.email(),
                usuarioVerificado.senha(), usuarioVerificado.telefone(), usuarioVerificado.endereco(), usuarioVerificado.tipo()
        );

        usuarioRepository.save(newUsuario);

        if (newUsuario.getTipo() == TipoDeUsuarioEnum.ALUNO) {
            criaMatricula(usuario, newUsuario);
        }

        return new UsuarioResponseCreate(newUsuario.getId());
    }

    public UsuarioResponseCreate updateUsuario(UUID usuarioId, UsuarioRequestUpdate usuarioRequest) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(null);
        if (usuario != null) {
            usuario.setNome(usuarioRequest.nome());
            usuario.setEmail(usuarioRequest.email());
            usuario.setSenha(usuarioRequest.senha());
            usuario.setTelefone(usuarioRequest.telefone());
            usuario.setEndereco(usuarioRequest.endereco());
            usuarioRepository.save(usuario);
            return new UsuarioResponseCreate(usuario.getId());
        }
        return null;
    }

    public UsuarioResponseFind findUsuario(UUID usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return toUsuarioResponseFind(usuario);
    }



    private UsuarioResponseFind toUsuarioResponseFind(Usuario usuario) {
        List<AulaResponseFindAluno> aulas = usuario.getAulas().stream()
                .map(this::toAulaResponseFind)
                .collect(Collectors.toList());
        return new UsuarioResponseFind(
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getEndereco(),
                aulas
        );
    }

    private AulaResponseFindAluno toAulaResponseFind(Aula aula) {
        return new AulaResponseFindAluno(
                aula.getTitulo(),
                aula.getInicioAula(),
                aula.getFimAula()
        );
    }

    private UsuarioRequest verificaExistencia(UsuarioRequest usuario) throws UsuarioFoundException {
        Usuario userCPF = this.usuarioRepository.findByCpf(usuario.cpf());
        Usuario userEmail = this.usuarioRepository.findByEmail(usuario.email());
        Usuario userTelefone = this.usuarioRepository.findByTelefone(usuario.telefone());
        if (userCPF != null || userEmail != null || userTelefone != null){
            throw new UsuarioFoundException();
        }
        return usuario;
    }

    private void criaMatricula(UsuarioRequest usuarioRequest, Usuario usuario ){
        Plano plano = this.planoRepository.findById(usuarioRequest.planoId()).orElse(null);

        Matricula newMatricula = new Matricula();
        newMatricula.setStatus(StatusDaMatricula.ATIVA);
        newMatricula.setDataPagamento(LocalDate.now());
        newMatricula.setDataVencimento(LocalDate.now().plusDays(30));
        newMatricula.setPlano(plano);
        newMatricula.setUsuario(usuario);

        matriculaRepository.save(newMatricula);
    }
}
