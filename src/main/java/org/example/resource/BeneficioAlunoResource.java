package org.example.resource;

import java.util.List;

import org.example.DTO.BeneficioAlunoDTO;
import org.example.DTO.BeneficioAlunoRequestDTO;
import org.example.domain.BeneficioAluno;
import org.example.mapper.BeneficioAlunoMapper;
import org.example.mapper.BeneficioAlunoRequestMapper;
import org.example.service.BeneficioAlunoService;

import jakarta.inject.Inject;
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

    @Inject
    BeneficioAlunoService beneficioAlunoService;

    @GET
    public List<BeneficioAluno> listAll() {
        return beneficioAlunoService.listarBeneficiados();
    }

    @GET
    @Path("/{id}")
    public BeneficioAlunoDTO findById(@PathParam("id") Long id) {
        BeneficioAluno beneficioAluno = beneficioAlunoService.buscarPorId(id);
        if (beneficioAluno == null) {
            throw new NotFoundException("Vinculo não encontrado");
        }
        return BeneficioAlunoMapper.toDTO(beneficioAluno);
    }

    @POST
    @Transactional
    public BeneficioAlunoDTO create(BeneficioAlunoDTO request) {
        BeneficioAluno beneficio = beneficioAlunoService.inserirAlunoBeneficio(request);
        return BeneficioAlunoMapper.toDTO(beneficio);
    }

    // @POST
    // @Path("/inserirAluno")
    // @Transactional
    // public BeneficioAlunoRequestDTO inserirAlunoBeneficio(BeneficioAlunoRequestDTO request) {
    //     BeneficioAluno beneficio = beneficioAlunoService.inserirAlunoBeneficio(request.alunoId, request.auxilioId);
    //     return  BeneficioAlunoRequestMapper.toDTO(beneficio);
    // }

    @PUT
    @Path("/{id}")
    @Transactional
    public BeneficioAlunoDTO update(@PathParam("id") Long id, BeneficioAlunoDTO updated) {
        BeneficioAluno beneficio = beneficioAlunoService.buscarPorId(id);
        if (beneficio == null) {
            throw new NotFoundException("Vinculo não encontrado");
        }

        beneficio.observacao = updated.observacao;
        beneficio.ativo = updated.ativo;

        return BeneficioAlunoMapper.toDTO(beneficio);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        boolean deleted = beneficioAlunoService.remover(id);
        if (!deleted) {
            throw new NotFoundException("Vinculo não encontrado");
        }
    }
}
