package org.example.mapper;
import org.example.DTO.BeneficioAlunoDTO;
import org.example.domain.BeneficioAluno;
import org.example.domain.Auxilio;
import org.example.domain.Aluno;

public class BeneficioAlunoMapper {
     public static BeneficioAlunoDTO toDTO(BeneficioAluno benficioaluno) {
        BeneficioAlunoDTO dto = new BeneficioAlunoDTO();
        dto.id = benficioaluno.id;
        dto.observacao = benficioaluno.observacao;
        dto.data_concessao = benficioaluno.data_concessao;
        dto.ativo = benficioaluno.ativo;
        dto.alunoId = benficioaluno.aluno != null ? benficioaluno.aluno.id : null;
        dto.auxilioId = benficioaluno.auxilio != null ? benficioaluno.auxilio.id : null;
        return dto;
    }
    // usado  para persisitr o dado no banco
    public static BeneficioAluno toEntity(BeneficioAlunoDTO dto) {
        BeneficioAluno benficioaluno = new BeneficioAluno();
        benficioaluno.observacao = dto.observacao;
        benficioaluno.data_concessao = dto.data_concessao;
        benficioaluno.ativo = dto.ativo;
        // cria um Auxilio só com o ID
        Auxilio aux = new Auxilio();
        aux.id = dto.auxilioId;
        // cria um Aluno só com o ID
        Aluno aluno = new Aluno();
        aluno.id = dto.alunoId;

        benficioaluno.aluno = aluno;
        benficioaluno.auxilio = aux;
        return benficioaluno;
    }
}
