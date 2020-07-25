package it.sup.mcepacientes.services.localizacao;

import it.sup.mcepacientes.dtos.localizacao.EnderecoRequestDTO;
import it.sup.mcepacientes.dtos.localizacao.EnderecoResponseDTO;
import it.sup.mcepacientes.entities.localizacao.Endereco;
import it.sup.mcepacientes.repository.localizacao.EnderecoRepository;
import it.sup.mcepacientes.services.interfaces.CrudService;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService implements CrudService<EnderecoRequestDTO, Long, EnderecoResponseDTO> {

    private final EnderecoRepository repository;
    private final Logger logger;

    public EnderecoService(EnderecoRepository repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public EnderecoResponseDTO findById(Long aLong) {
        logger.debug("Buscando os enderecos do paciente");
        Endereco entity = this.repository.findById(aLong)
                .orElseThrow(EntityNotFoundException::new);

        return getResponseDto(entity);
    }

    @Override
    public List<EnderecoResponseDTO> findAll() {
        List<Endereco> all = this.repository.findAll();
        List<EnderecoResponseDTO> dtos = new ArrayList<>();

        all.parallelStream().forEach(endereco -> {
            EnderecoResponseDTO dto = getResponseDto(endereco);
            dtos.add(dto);
        });
        return dtos;
    }

    @Override
    public Page<EnderecoResponseDTO> findAllPageable(Pageable pageable) {
        List<EnderecoResponseDTO> all = this.findAll();

        return new PageImpl<>(all, pageable, 10);
    }

    @Override
    public EnderecoResponseDTO save(EnderecoRequestDTO entity) {
        try {
            Endereco save = this.repository.save(getEndereco(entity));
            return getResponseDto(save);
        } catch (HibernateError error) {
            throw new HibernateError(error.getMessage());
        }
    }

    @Override
    public EnderecoResponseDTO update(Long id, EnderecoRequestDTO entity) {
        Optional<Endereco> endereco = this.repository.findById(id);
        EnderecoResponseDTO dto = endereco.map(end -> {
            setEndereco(entity, end);
            Endereco updated = this.repository.save(end);

            return this.getResponseDto(updated);
        }).orElseThrow(PersistenceException::new);
        return dto;
    }

    @Override
    public void delete(Long aLong) {
        try {
            this.repository.deleteById(aLong);
        } catch (HibernateException e) {
            throw new HibernateError(e.getMessage());
        }
    }

    private Endereco getEndereco(EnderecoRequestDTO entity) {
        Endereco end = new Endereco();
        setEndereco(entity, end);
        return end;
    }

    private void setEndereco(EnderecoRequestDTO entity, Endereco end) {
        end.setBairro(entity.getBairro());
        end.setCep(entity.getCep());
        end.setComplemento(entity.getComplemento());
        end.setEstado(entity.getEstado());
        end.setIsActived(entity.getIsActived());
        end.setLogradouro(entity.getLogradouro());
        end.setMunicipio(entity.getMunicipio());
        end.setNumero(entity.getNumero());
        end.setPacientes(entity.getPacientes());
        end.setUpdateTimestamp(Calendar.getInstance());
    }

    private EnderecoResponseDTO getResponseDto(Endereco resource) {
        return EnderecoResponseDTO
                .builder()
                .bairro(resource.getBairro())
                .cep(resource.getCep())
                .complemento(resource.getComplemento())
                .estado(resource.getEstado())
                .logradouro(resource.getLogradouro())
                .municipio(resource.getMunicipio())
                .numero(resource.getNumero())
                .observacoes(resource.getObservacoes())
                .build();
    }


}
