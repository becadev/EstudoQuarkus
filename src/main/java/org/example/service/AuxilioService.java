package org.example.service;
import java.util.List;

import org.example.domain.Auxilio;
import org.example.repository.AuxilioRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AuxilioService {
    
    @Inject
    AuxilioRepository repository;

    public List<Auxilio> listarAuxilios() {
        return repository.listAll();
    }

    public Auxilio buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void salvar(Auxilio auxilio) {
        repository.persist(auxilio);
    }   

    public boolean remover(Long id) {
        return repository.deleteById(id);
    }   

}
