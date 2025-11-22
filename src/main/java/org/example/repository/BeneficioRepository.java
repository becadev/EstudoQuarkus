package org.example.repository;
import org.example.domain.Beneficio;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BeneficioRepository implements PanacheRepository<Beneficio> {

}