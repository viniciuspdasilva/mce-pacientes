package it.sup.mcepacientes.entities.localizacao;

import it.sup.mcepacientes.dtos.localizacao.EnderecoRequestDTO;
import it.sup.mcepacientes.dtos.localizacao.EnderecoResponseDTO;
import it.sup.mcepacientes.entities.SupperMappedEntity;
import it.sup.mcepacientes.entities.pacientes.Paciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
public class Endereco extends SupperMappedEntity {

    private String logradouro;

    private String numero;

    private String complemento;

    private Long bairro;

    private Long municipio;

    private Long estado;

    private String cep;

    private String observacoes;

    private Boolean isActived;

    @ManyToMany
    @JoinColumn
    private List<Paciente> pacientes;

    public Endereco() {
    }

    public static Endereco mockRequestDTO(EnderecoRequestDTO dto) {
        return new Endereco(
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getMunicipio(),
                dto.getEstado(),
                dto.getCep(),
                dto.getObservacoes(),
                dto.getIsActived(),
                dto.getPacientes()
        );
    }

    public static EnderecoResponseDTO mockResponse(Endereco dto) {
        return new EnderecoResponseDTO(
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getMunicipio(),
                dto.getEstado(),
                dto.getCep(),
                dto.getObservacoes()
        );
    }
}
