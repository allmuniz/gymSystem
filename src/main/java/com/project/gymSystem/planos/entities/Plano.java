package com.project.gymSystem.planos.entities;

import com.project.gymSystem.matriculas.entities.Matricula;
import com.project.gymSystem.planos.enuns.TipoDePlanoEnum;
import com.project.gymSystem.usuarios.entities.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "planos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private TipoDePlanoEnum tipo;

    private String beneficios;

    private double preco;

    public Plano(TipoDePlanoEnum tipo, String beneficios, double preco) {
        this.tipo = tipo;
        this.beneficios = beneficios;
        this.preco = preco;
    }
}
