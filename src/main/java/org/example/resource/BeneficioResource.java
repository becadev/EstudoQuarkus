package org.example.resource;

import java.util.List;

import org.example.domain.Beneficio;

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

@Path("/beneficio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BeneficioResource {

    @GET
    public List<Beneficio> listAll() {
        return Beneficio.listAll();
    }

    @GET
    @Path("/{id}")
    public Beneficio findById(@PathParam("id") Long id) {
        Beneficio beneficio = Beneficio.findById(id);
        if (beneficio == null) {
            throw new NotFoundException("Beneficio não encontrado");
        }
        return beneficio;
    }

    @POST
    @Transactional
    public Beneficio create(Beneficio beneficiorequest) {
        Beneficio beneficio = new Beneficio();
        beneficio.nome = beneficiorequest.nome;
        beneficio.descricao = beneficiorequest.descricao;
        beneficio.ativo = beneficiorequest.ativo;

        beneficio.persist();
        return beneficio;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Beneficio update(@PathParam("id") Long id, Beneficio updated) {
        Beneficio beneficio = Beneficio.findById(id);
        if (beneficio == null) {
            throw new NotFoundException("Beneficio não encontrado");
        }

        beneficio.nome = updated.nome;
        beneficio.descricao = updated.descricao;
        beneficio.ativo = updated.ativo;

        return beneficio;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        boolean deleted = Beneficio.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Beneficio não encontrado");
        }
    }
}
