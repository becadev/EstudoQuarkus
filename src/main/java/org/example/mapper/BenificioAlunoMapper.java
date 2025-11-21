package org.example.mapper;
import org.example.DTO.BeneficioAlunoDTO;
import org.example.domain.BeneficioAluno;

public class BenificioAlunoMapper {
     public static BeneficioAlunoDTO toDTO(BeneficioAluno benficioaluno) {
        BeneficioAlunoDTO dto = new BeneficioAlunoDTO();
        dto.id = benficioaluno.id;
        dto.observacao = benficioaluno.observacao;
        dto.data_concessao = benficioaluno.data_concessao;
        dto.ativo = benficioaluno.ativo;
        dto.aluno = benficioaluno.aluno;
        dto.auxilio = benficioaluno.auxilio;
        return dto;
    }
    // usado  para persisitr o dado no banco
    public static BeneficioAluno toEntity(BeneficioAlunoDTO dto) {
        BeneficioAluno benficioaluno = new BeneficioAluno();
        benficioaluno.observacao = dto.observacao;
        benficioaluno.data_concessao = dto.data_concessao;
        benficioaluno.ativo = dto.ativo;
        benficioaluno.aluno = dto.aluno;
        benficioaluno.auxilio = dto.auxilio;
        return benficioaluno;
    }
}
