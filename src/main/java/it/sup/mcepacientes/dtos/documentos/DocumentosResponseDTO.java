package it.sup.mcepacientes.dtos.documentos;

import it.sup.mcepacientes.entities.documentos.Emissores;
import it.sup.mcepacientes.entities.documentos.TypeDocumento;
import lombok.*;

import java.util.Calendar;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DocumentosResponseDTO {

    private TypeDocumento type;

    private String value;

    private Calendar dataVencimento;

    private Calendar dataEmissao;

    private Emissores emissor;
}
