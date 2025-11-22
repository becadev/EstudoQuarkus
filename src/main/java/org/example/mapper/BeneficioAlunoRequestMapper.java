package org.example.mapper;
import org.example.DTO.BeneficioAlunoRequestDTO;
import org.example.domain.BeneficioAluno;

public class BeneficioAlunoRequestMapper {
     public static BeneficioAlunoRequestDTO toDTO(BeneficioAluno benficioaluno) {
        BeneficioAlunoRequestDTO dto = new BeneficioAlunoRequestDTO();
        dto.alunoId = benficioaluno.aluno.id;
        dto.auxilioId = benficioaluno.auxilio.id;
        return dto;
    }
    // usado  para persisitr o dado no banco
    public static BeneficioAluno toEntity(BeneficioAlunoRequestDTO dto) {
        BeneficioAluno benficioaluno = new BeneficioAluno();
        benficioaluno.aluno.id = dto.alunoId;
        benficioaluno.auxilio.id = dto.auxilioId;

        return benficioaluno;
    }
}
