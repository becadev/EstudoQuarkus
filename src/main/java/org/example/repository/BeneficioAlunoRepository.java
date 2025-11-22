package org.example.repository;
import org.example.domain.Aluno;
import org.example.domain.Auxilio;
import org.example.domain.BeneficioAluno;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeneficioAlunoRepository implements PanacheRepository<BeneficioAluno> {
    public BeneficioAluno buscarAtivo(Aluno aluno, Auxilio auxilio) {
        return find("aluno = ?1 AND auxilio = ?2 AND ativo = true", // faz uma consulta JPQL, ?1 vai ser substituído por aluno e ?2 por auxilio
                aluno, auxilio).firstResult(); // vai retornar o primeiro resultado encontrado ou null
    }
}