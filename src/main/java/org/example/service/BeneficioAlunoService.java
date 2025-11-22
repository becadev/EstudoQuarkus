package org.example.service;
import java.util.List;

import org.example.domain.Aluno;
import org.example.domain.Auxilio;
import org.example.domain.BeneficioAluno;
import org.example.repository.AlunoRepository;
import org.example.repository.AuxilioRepository;
import org.example.repository.BeneficioAlunoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class BeneficioAlunoService {

    @Inject
    BeneficioAlunoRepository repository;

    @Inject
    AuxilioRepository auxilioRepository;

    @Inject
    AlunoRepository alunoRepository;

    public List<BeneficioAluno> listarBeneficiados() {
        return repository.listAll();
    }

    public BeneficioAluno buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void salvar(BeneficioAluno beneficiado) {
        repository.persist(beneficiado);
    }

    @Transactional
    public boolean remover(Long id) {
        return repository.deleteById(id);
    }

    @Transactional
    public BeneficioAluno inserirAlunoBeneficio(Long alunoId, Long auxilioId) {

        // 1. Busca o benefício (auxílio) correto e o aluno de acordo com id fornecido 
        Auxilio auxilio = auxilioRepository.findById(auxilioId);
        Aluno aluno = alunoRepository.findById(alunoId);

        if (auxilio == null) {
            throw new IllegalArgumentException("Auxílio não encontrado");
        }

        // 2. Verifica se esse aluno já tem esse mesmo benefício ativo
        BeneficioAluno beneficioAtivo = repository.buscarAtivo(aluno, auxilio);

        if (beneficioAtivo != null) {
            throw new IllegalArgumentException("Aluno já possui esse benefício ativo");
        }

        // 3. Cria um novo vínculo
        BeneficioAluno novo = new BeneficioAluno();
        novo.aluno = aluno;
        novo.auxilio = auxilio;
        novo.ativo = true;

        repository.persist(novo);
        return novo;
    }
}
