package org.example.resource;
import java.util.List;

import org.example.domain.Auxilio;

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

@Path("/auxilio")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuxilioResource {

    @GET
    public List<Auxilio> listAll() {
        return Auxilio.listAll();
    }

    @GET
    @Path("/{id}")
    public Auxilio findById(@PathParam("id") Long id) {
        Auxilio auxilio = Auxilio.findById(id);
        if (auxilio == null) {
            throw new NotFoundException("Auxilio não encontrado");
        }
        return auxilio;
    }

    @POST
    @Transactional
    public Auxilio create(Auxilio auxiliorequest) {
        Auxilio auxilio = new Auxilio();
        auxilio.titulo = auxiliorequest.titulo;
        auxilio.descricao = auxiliorequest.descricao;
        auxilio.ativo = auxiliorequest.ativo;

        auxilio.persist();
        return auxilio;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Auxilio update(@PathParam("id") Long id, Auxilio updated) {
        Auxilio auxilio = Auxilio.findById(id);
        if (auxilio == null) {
            throw new NotFoundException("Auxilio não encontrado");
        }

        auxilio.titulo = updated.titulo;
        auxilio.descricao = updated.descricao;
        auxilio.ativo = updated.ativo;

        return auxilio;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        boolean deleted = Auxilio.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Auxilio não encontrado");
        }
    }
}
