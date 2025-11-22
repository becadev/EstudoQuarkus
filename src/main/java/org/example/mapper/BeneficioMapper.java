package org.example.mapper;

import org.example.DTO.BeneficioDTO;
import org.example.domain.Beneficio;


public class BeneficioMapper {
    // usado na api para entrada e saida de dados
    public static BeneficioDTO toDTO(Beneficio benificio) {
        BeneficioDTO dto = new BeneficioDTO();
        dto.id = benificio.id;
        dto.descricao = benificio.descricao;
        dto.ativo = benificio.ativo;
        dto.auxilioId = benificio.auxilio.id;
        return dto;
    }
    // usado  para persisitr o dado no banco
    public static Beneficio toEntity(BeneficioDTO dto) {
        Beneficio benificio = new Beneficio();
        benificio.descricao = dto.descricao;
        benificio.ativo = dto.ativo;
        benificio.auxilio.id = dto.auxilioId;
        return benificio;
    }
}
