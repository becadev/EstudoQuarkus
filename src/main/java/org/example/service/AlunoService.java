package org.example.service;

import java.util.List;

import org.example.domain.Aluno;
import org.example.repository.AlunoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AlunoService {

    @Inject
    AlunoRepository repository;

    public List<Aluno> listarAlunos() {
        return repository.listAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void salvar(Aluno aluno) {
        repository.persist(aluno);
    }

    public boolean remover(Long id) {
        return repository.deleteById(id);
    }
}
