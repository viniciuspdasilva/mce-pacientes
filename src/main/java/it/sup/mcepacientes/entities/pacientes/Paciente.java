package it.sup.mcepacientes.entities.pacientes;

import it.sup.mcepacientes.entities.SupperMappedEntity;
import it.sup.mcepacientes.entities.documentos.Documentos;
import it.sup.mcepacientes.entities.localizacao.Endereco;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
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

}
