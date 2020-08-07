package it.sup.mcepacientes.dtos.localizacao;

import lombok.*;
import lombok.extern.log4j.Log4j2;

@Builder
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoResponseDTO {

    private String logradouro;

    private String numero;

    private String complemento;

    private Long bairro;

    private Long municipio;

    private Long estado;

    private String cep;

    private String observacoes;

}
