package com.project.gymSystem.matriculas.entities;

import com.project.gymSystem.matriculas.enuns.StatusDaMatricula;
import com.project.gymSystem.planos.entities.Plano;
import com.project.gymSystem.usuarios.entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "matriculas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StatusDaMatricula status;

    private LocalDate dataPagamento;

    private LocalDate dataVencimento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;
}
