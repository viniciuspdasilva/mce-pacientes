package it.sup.mcepacientes.entities.documentos;

import it.sup.mcepacientes.entities.SupperMappedEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Calendar;

@Entity
public class Documentos  extends SupperMappedEntity {

    @Enumerated(value = EnumType.STRING)
    private TypeDocumento type;

    private String value;

    private Calendar dataVencimento;

    private Calendar dataEmissao;

    @Enumerated(value = EnumType.STRING)
    private Emissores emissor;
}
