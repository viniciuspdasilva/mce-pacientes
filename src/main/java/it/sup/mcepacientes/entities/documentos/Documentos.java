package it.sup.mcepacientes.entities.documentos;

import it.sup.mcepacientes.dtos.documentos.DocumentosRequestDTO;
import it.sup.mcepacientes.dtos.documentos.DocumentosResponseDTO;
import it.sup.mcepacientes.entities.SupperMappedEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Documentos extends SupperMappedEntity {

    @Enumerated(value = EnumType.STRING)
    private TypeDocumento type;

    private String value;

    private Calendar dataVencimento;

    private Calendar dataEmissao;

    @Enumerated(value = EnumType.STRING)
    private Emissores emissor;

    public static DocumentosResponseDTO responseDTO(Documentos documentos) {
        return DocumentosResponseDTO
                .builder()
                .dataEmissao(documentos.getDataEmissao())
                .dataVencimento(documentos.getDataVencimento())
                .emissor(documentos.getEmissor())
                .type(documentos.getType())
                .value(documentos.getValue())
                .build();
    }

    public static Documentos transform(DocumentosRequestDTO documentos) {
        return Documentos
                .builder()
                .dataEmissao(documentos.getDataEmissao())
                .dataVencimento(documentos.getDataVencimento())
                .emissor(documentos.getEmissor())
                .type(documentos.getType())
                .value(documentos.getValue())
                .build();
    }

    public static Documentos updated(Documentos documentos) {
        return Documentos
                .builder()
                .dataEmissao(documentos.getDataEmissao())
                .dataVencimento(documentos.getDataVencimento())
                .emissor(documentos.getEmissor())
                .type(documentos.getType())
                .value(documentos.getValue())
                .build();
    }

    @PrePersist
    public void prePersist() {
        super.setCreateTimestamp(Calendar.getInstance());
    }

    @PreUpdate
    public void preUpdated() {
        super.setUpdateTimestamp(Calendar.getInstance());
    }
}
