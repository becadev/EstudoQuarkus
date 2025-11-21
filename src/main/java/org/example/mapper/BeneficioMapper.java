package org.example.mapper;

import org.example.DTO.BenefiicioDTO;
import org.example.domain.Beneficio;


public class BeneficioMapper {
    // usado na api para entrada e saida de dados
    public static BenefiicioDTO toDTO(Beneficio benificio) {
        BenefiicioDTO dto = new BenefiicioDTO();
        dto.id = benificio.id;
        dto.descricao = benificio.descricao;
        dto.ativo = benificio.ativo;
        return dto;
    }
    // usado  para persisitr o dado no banco
    public static Beneficio toEntity(BenefiicioDTO dto) {
        Beneficio benificio = new Beneficio();
        benificio.descricao = dto.descricao;
        benificio.ativo = dto.ativo;
        return benificio;
    }
}
