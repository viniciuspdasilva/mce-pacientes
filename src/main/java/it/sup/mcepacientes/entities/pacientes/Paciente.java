package it.sup.mcepacientes.entities.pacientes;

import it.sup.mcepacientes.dtos.localizacao.EnderecoRequestDTO;
import it.sup.mcepacientes.dtos.paciente.PacienteRequestDTO;
import it.sup.mcepacientes.dtos.paciente.PacienteResponseDTO;
import it.sup.mcepacientes.entities.SupperMappedEntity;
import it.sup.mcepacientes.entities.documentos.Documentos;
import it.sup.mcepacientes.entities.localizacao.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Paciente extends SupperMappedEntity {

    private String firstname;

    private String middlename;

    private String lastname;

    private String dateBirth;

    private String nationality;

    @ManyToMany
    @JoinColumn
    private List<Paciente> family;

    @OneToMany
    private List<Documentos> documents;

    @ManyToMany
    @JoinColumn
    private List<Endereco> enderecos;

    public static Paciente mockRequestDTO(PacienteRequestDTO entity) {
        return new Paciente(
                entity.getFirstname(),
                entity.getMiddlename(),
                entity.getLastname(),
                entity.getDateBirth(),
                entity.getNationality(),
                entity.getFamily(),
                entity.getDocuments(),
                entity.getEnderecos()
        );
    }
    public static PacienteResponseDTO mockResponse(Paciente entity) {
        return new PacienteResponseDTO(
                entity.getFirstname(),
                entity.getMiddlename(),
                entity.getLastname(),
                entity.getDateBirth(),
                entity.getNationality(),
                entity.getFamily(),
                entity.getDocuments(),
                entity.getEnderecos()
        );
    }
}
