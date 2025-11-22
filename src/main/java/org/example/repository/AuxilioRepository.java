package org.example.repository;
import org.example.domain.Auxilio;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuxilioRepository implements PanacheRepository<Auxilio> {

}