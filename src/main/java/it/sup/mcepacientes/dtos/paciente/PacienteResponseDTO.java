package it.sup.mcepacientes.dtos.paciente;

import it.sup.mcepacientes.entities.documentos.Documentos;
import it.sup.mcepacientes.entities.localizacao.Endereco;
import it.sup.mcepacientes.entities.pacientes.Paciente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Builder
@Data
@Log4j2
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDTO {

    private String firstname;

    private String middlename;

    private String lastname;

    private String dateBirth;

    private String nationality;

    private List<Paciente> family;

    private List<Documentos> documents;

    private List<Endereco> enderecos;


}
