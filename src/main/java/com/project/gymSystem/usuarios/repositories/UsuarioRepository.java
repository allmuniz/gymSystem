package com.project.gymSystem.usuarios.repositories;

import com.project.gymSystem.usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    public Usuario findByCpf(String cpf);
    public Usuario findByEmail(String email);
    public Usuario findByTelefone(String telefone);
}
