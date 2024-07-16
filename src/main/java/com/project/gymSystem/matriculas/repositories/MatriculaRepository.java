package com.project.gymSystem.matriculas.repositories;

import com.project.gymSystem.matriculas.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatriculaRepository extends JpaRepository<Matricula, UUID> {
}
