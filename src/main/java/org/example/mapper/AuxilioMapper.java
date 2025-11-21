package org.example.mapper;

import org.example.DTO.AuxilioDTO;
import org.example.domain.Auxilio;


public class AuxilioMapper {
    // usado na api para entrada e saida de dados
    public static AuxilioDTO toDTO(Auxilio auxlio) {
        AuxilioDTO dto = new AuxilioDTO();
        dto.id = auxlio.id;
        dto.descricao = auxlio.descricao;
        dto.ativo = auxlio.ativo;
        return dto;
    }
    // usado  para persisitr o dado no banco
    public static Auxilio toEntity(AuxilioDTO dto) {
        Auxilio auxlio = new Auxilio();
        auxlio.descricao = dto.descricao;
        auxlio.ativo = dto.ativo;
        return auxlio;
    }
}
