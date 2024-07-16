package com.project.gymSystem.aulas.repositories;

import com.project.gymSystem.aulas.entities.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AulaRepository extends JpaRepository<Aula, UUID> {
}
