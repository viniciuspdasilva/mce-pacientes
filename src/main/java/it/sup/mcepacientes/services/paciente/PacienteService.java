package it.sup.mcepacientes.services.paciente;

import it.sup.mcepacientes.dtos.localizacao.EnderecoRequestDTO;
import it.sup.mcepacientes.dtos.paciente.PacienteRequestDTO;
import it.sup.mcepacientes.dtos.paciente.PacienteResponseDTO;
import it.sup.mcepacientes.entities.localizacao.Endereco;
import it.sup.mcepacientes.entities.pacientes.Paciente;
import it.sup.mcepacientes.repository.paciente.PacienteRepository;
import it.sup.mcepacientes.services.interfaces.CrudService;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements CrudService<PacienteRequestDTO, Long, PacienteResponseDTO> {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public PacienteResponseDTO findById(Long aLong) {
        Paciente paciente = this.repository.findById(aLong)
                .orElseThrow(EntityNotFoundException::new);
        return setPacienteResponseDto(paciente);
    }

    private PacienteResponseDTO setPacienteResponseDto(Paciente paciente) {
        return PacienteResponseDTO
                .builder()
                .dateBirth(paciente.getDateBirth())
                .documents(paciente.getDocuments())
                .enderecos(paciente.getEnderecos())
                .family(paciente.getFamily())
                .firstname(paciente.getFirstname())
                .lastname(paciente.getLastname())
                .middlename(paciente.getMiddlename())
                .nationality(paciente.getNationality())
                .build();
    }

    @Override
    public List<PacienteResponseDTO> findAll() {
        List<Paciente> all = this.repository.findAll();
        List<PacienteResponseDTO> pacientes = new ArrayList<>();
        all.parallelStream().forEach(paciente -> {
            PacienteResponseDTO dto = setPacienteResponseDto(paciente);
            pacientes.add(dto);
        });
        return pacientes;
    }

    @Override
    public Page<PacienteResponseDTO> findAllPageable(Pageable pageable) {
        List<PacienteResponseDTO> all = this.findAll();

        return new PageImpl<>(all, pageable, 10);
    }

    @Override
    public PacienteResponseDTO save(PacienteRequestDTO entity) {
        Paciente endereco = Paciente.mockRequestDTO(entity);

        Paciente save = this.repository.save(endereco);

        return Paciente.mockResponse(save);
    }

    @SneakyThrows
    @Override
    public PacienteResponseDTO update(Long aLong, PacienteRequestDTO entity) {
        Paciente paciente = Paciente.mockRequestDTO(entity);

        return this.repository.findById(aLong)
                .map(pac -> {
                    pac.setFirstname(paciente.getFirstname());
                    pac.setMiddlename(paciente.getMiddlename());
                    pac.setLastname(paciente.getLastname());
                    pac.setDateBirth(paciente.getDateBirth());
                    pac.setNationality(paciente.getNationality());
                    pac.setFamily(paciente.getFamily());
                    pac.setDocuments(paciente.getDocuments());
                    pac.setEnderecos(paciente.getEnderecos());
                    Paciente updated = this.repository.save(pac);
                    return Paciente.mockResponse(updated);
                }).orElseThrow(Exception::new);
    }

    @Override
    public void delete(Long aLong) {
        this.repository.deleteById(aLong);
    }

}
