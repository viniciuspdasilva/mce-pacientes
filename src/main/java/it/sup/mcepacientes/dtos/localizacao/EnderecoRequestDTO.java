package it.sup.mcepacientes.dtos.localizacao;

import it.sup.mcepacientes.entities.pacientes.Paciente;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Builder
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoRequestDTO {

    private String logradouro;

    private String numero;

    private String complemento;

    private Long bairro;

    private Long municipio;

    private Long estado;

    private String cep;

    private String observacoes;

    private Boolean isActived;

    private List<Paciente> pacientes;
}
