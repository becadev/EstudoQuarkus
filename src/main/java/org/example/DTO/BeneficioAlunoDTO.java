package  org.example.DTO;
import java.time.LocalDate;

import org.example.domain.Aluno;
import org.example.domain.Auxilio;

public class BeneficioAlunoDTO {
    public Long id;
    public String observacao;
    public LocalDate data_concessao;
    public Boolean ativo;
    public Aluno aluno;
    public Auxilio auxilio;
}
