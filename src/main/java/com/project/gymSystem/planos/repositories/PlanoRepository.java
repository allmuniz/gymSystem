package com.project.gymSystem.planos.repositories;

import com.project.gymSystem.planos.entities.Plano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlanoRepository extends JpaRepository<Plano, UUID> {
}
