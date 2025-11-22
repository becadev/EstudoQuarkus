package org.example.service;
import java.util.List;

import org.example.domain.Beneficio;
import org.example.repository.BeneficioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BeneficioService {
    
    @Inject
    BeneficioRepository repository;

    public List<Beneficio> listarBeneficio() {
        return repository.listAll();
    }

    public Beneficio buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void salvar(Beneficio auxilio) {
        repository.persist(auxilio);
    }   

    public boolean remover(Long id) {
        return repository.deleteById(id);
    }   

}
