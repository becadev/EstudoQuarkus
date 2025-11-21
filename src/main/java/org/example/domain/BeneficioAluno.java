package org.example.domain;

import java.time.LocalDate;

import io.quarkus.hibernate.orm.panache.PanacheEntity; // já gera o id automaticamente
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class BeneficioAluno extends PanacheEntity {
    
    public String observacao;
    public LocalDate data_concessao;
    public Boolean ativo;

    @ManyToOne
    @JoinColumn(name = "aluno_id") // nome da coluna FK
    public Aluno aluno;

    @OneToOne
    @JoinColumn(name = "auxilio_id") // nome da coluna FK
    public Auxilio auxilio;

}
