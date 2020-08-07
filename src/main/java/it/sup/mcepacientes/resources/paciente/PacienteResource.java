package it.sup.mcepacientes.resources.paciente;

import it.sup.mcepacientes.dtos.paciente.PacienteRequestDTO;
import it.sup.mcepacientes.dtos.paciente.PacienteResponseDTO;
import it.sup.mcepacientes.resources.util.ResponseUtilities;
import it.sup.mcepacientes.services.paciente.PacienteService;
import org.hibernate.HibernateException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteResource {

    private final PacienteService service;


    public PacienteResource(PacienteService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        try {
            List<PacienteResponseDTO> all = this.service.findAll();
            return ResponseEntity.ok(all);

        } catch (HibernateException | BadRequestException exception) {
            return retornaExcecao(exception);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAllPageable(Pageable pageable) {
        try {
            Page<PacienteResponseDTO> all = this.service.findAllPageable(pageable);
            return ResponseEntity.ok(all);

        } catch (HibernateException | BadRequestException exception) {
            return retornaExcecao(exception);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {
        try {
            PacienteResponseDTO byId = this.service.findById(id);
            return ResponseEntity.ok(byId);
        } catch (HibernateException | BadRequestException exception) {
            return retornaExcecao(exception);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody PacienteRequestDTO dto) {
        try {
            PacienteResponseDTO save = this.service.save(dto);
            URI url = URI.create("");
            return ResponseEntity.created(url).body(save);
        } catch (HibernateException | BadRequestException exception) {
            return retornaExcecao(exception);
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PacienteRequestDTO dto) {
        try {
            PacienteResponseDTO update = this.service.update(id, dto);
            return ResponseEntity.noContent().build();
        } catch (HibernateException | BadRequestException exception) {
            return retornaExcecao(exception);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            this.service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (HibernateException exception) {
            return retornaExcecao(exception);
        }

    }
    private ResponseEntity<?> retornaExcecao(RuntimeException exception) {
        return ResponseUtilities.errorServer(exception);
    }


}
