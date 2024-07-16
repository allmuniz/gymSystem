package com.project.gymSystem.planos.services;

import com.project.gymSystem.planos.dtos.PlanoRequest;
import com.project.gymSystem.planos.dtos.PlanoRequestUpdate;
import com.project.gymSystem.planos.dtos.PlanoResponseCreate;
import com.project.gymSystem.planos.dtos.PlanoResponseFind;
import com.project.gymSystem.planos.entities.Plano;
import com.project.gymSystem.planos.repositories.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PlanoService {

    private final PlanoRepository planoRepository;

    public PlanoService(PlanoRepository planoRepository) {
        this.planoRepository = planoRepository;
    }

    public PlanoResponseCreate registerPlano(PlanoRequest plano){
        Plano newPlano = new Plano();
        newPlano.setTipo(plano.tipo());
        newPlano.setBeneficios(plano.beneficios());
        newPlano.setPreco(plano.preco());

        this.planoRepository.save(newPlano);
        return new PlanoResponseCreate(newPlano.getId());
    }

    public PlanoResponseCreate updatePlano(UUID id, PlanoRequestUpdate planoRequest){
        Plano plano = this.planoRepository.findById(id).orElse(null);
        if(plano != null){
            plano.setBeneficios(planoRequest.beneficios());
            plano.setPreco(planoRequest.preco());
            this.planoRepository.save(plano);
            return new PlanoResponseCreate(plano.getId());
        }
        return null;
    }

    public PlanoResponseFind findPlano(UUID planoId){
        Plano planoFind = this.planoRepository.findById(planoId).orElse(null);
        return new PlanoResponseFind(planoFind.getTipo(),
                planoFind.getBeneficios(),
                planoFind.getPreco());
    }

    public void deletePlano(UUID planoId){
        this.planoRepository.deleteById(planoId);
    }
}
