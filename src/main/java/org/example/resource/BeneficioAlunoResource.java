package org.example.resource;

import java.util.List;

import org.example.domain.BeneficioAluno;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/beneficioAluno")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficioAlunoResource {

    @GET
    public List<BeneficioAluno> listAll() {
        return BeneficioAluno.listAll();
    }

    @GET
    @Path("/{id}")
    public BeneficioAluno findById(@PathParam("id") Long id) {
        BeneficioAluno beneficioAluno = BeneficioAluno.findById(id);
        if (beneficioAluno == null) {
            throw new NotFoundException("Vinculo não encontrado");
        }
        return beneficioAluno;
    }

    @POST
    @Transactional
    public BeneficioAluno create(BeneficioAluno beneficiorequest) {
        BeneficioAluno beneficio = new BeneficioAluno();
        beneficio.observacao = beneficiorequest.observacao;
        beneficio.ativo = beneficiorequest.ativo;
        beneficio.aluno = beneficiorequest.aluno;
        beneficio.auxilio = beneficiorequest.auxilio;

        beneficio.persist();
        return beneficio;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public BeneficioAluno update(@PathParam("id") Long id, BeneficioAluno updated) {
        BeneficioAluno beneficio = BeneficioAluno.findById(id);
        if (beneficio == null) {
            throw new NotFoundException("Vinculo não encontrado");
        }

        beneficio.observacao = updated.observacao;
        beneficio.ativo = updated.ativo;

        return beneficio;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        boolean deleted = BeneficioAluno.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Vinculo não encontrado");
        }
    }
}
