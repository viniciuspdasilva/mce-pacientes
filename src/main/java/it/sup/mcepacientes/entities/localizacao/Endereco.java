package it.sup.mcepacientes.entities.localizacao;

import it.sup.mcepacientes.entities.SupperMappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
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
}
