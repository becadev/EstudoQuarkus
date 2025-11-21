package org.example.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.transaction.Transactional;
import java.util.List;
import org.example.domain.Aluno;

@Path("/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {

    @GET
    public List<Aluno> listAll() {
        return Aluno.listAll();
    }

    @GET
    @Path("/{id}")
    public Aluno findById(@PathParam("id") Long id) {
        Aluno aluno = Aluno.findById(id);
        if (aluno == null) {
            throw new NotFoundException("Aluno não encontrado");
        }
        return aluno;
    }

    @POST
    @Transactional
    public Aluno create(Aluno alunoRequest) {
        Aluno aluno = new Aluno();
        aluno.nome = alunoRequest.nome;
        aluno.email = alunoRequest.email;
        aluno.ativo = alunoRequest.ativo;

        aluno.persist();
        return aluno;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Aluno update(@PathParam("id") Long id, Aluno updated) {
        Aluno aluno = Aluno.findById(id);
        if (aluno == null) {
            throw new NotFoundException("Aluno não encontrado");
        }

        aluno.nome = updated.nome;
        aluno.email = updated.email;
        aluno.ativo = updated.ativo;

        return aluno;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        boolean deleted = Aluno.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Aluno não encontrado");
        }
    }
}
