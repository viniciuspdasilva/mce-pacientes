package it.sup.mcepacientes.resources.documentos;

import it.sup.mcepacientes.dtos.documentos.DocumentosRequestDTO;
import it.sup.mcepacientes.dtos.documentos.DocumentosResponseDTO;
import it.sup.mcepacientes.resources.util.ResponseUtilities;
import it.sup.mcepacientes.services.documentos.DocumentosService;
import org.hibernate.HibernateException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.net.URI;
import java.util.Locale;

@RestController
@RequestMapping({"/documentos"})
public class DocumentosResource {

    private final DocumentosService service;
    private final ResponseUtilities<DocumentosResponseDTO, Long> responseUtilities;

    public DocumentosResource(DocumentosService service) {
        this.service = service;
        this.responseUtilities = new ResponseUtilities<>();
    }

    @GetMapping("/all")
    public ResponseEntity<?> listAll() {
        try {
            return ResponseEntity.ok(service.findAll());
        } catch (BadRequestException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findByID(@PathVariable Long id) throws Exception {
        DocumentosResponseDTO documentoSelected = this.service.findById(id);
        return ResponseEntity.ok(documentoSelected);
    }
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody DocumentosRequestDTO documentosRequestDTO) {
        try {
            DocumentosResponseDTO save = this.service.save(documentosRequestDTO);
            URI direcionamento = URI.create("/api/v1/documentos/search/"
                    .concat(save.getType().toString()).toLowerCase(Locale.ENGLISH)
                    .concat("/")
                    .concat(save.getValue()));
            return ResponseEntity.created(direcionamento).body(save);
        } catch (BadRequestException ex) {
            return ResponseUtilities.errorServer(ex);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DocumentosRequestDTO dto) {
        try {
            this.service.update(id, dto);
            return ResponseEntity.noContent().build();
        } catch (Exception exception) {
            return ResponseUtilities.errorServer(exception);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (HibernateException | BadRequestException exception) {
            return ResponseUtilities.errorServer(exception);
        }
    }
}
