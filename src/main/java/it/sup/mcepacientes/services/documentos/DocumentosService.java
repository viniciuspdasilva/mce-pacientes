package it.sup.mcepacientes.services.documentos;

import it.sup.mcepacientes.dtos.documentos.DocumentosRequestDTO;
import it.sup.mcepacientes.dtos.documentos.DocumentosResponseDTO;
import it.sup.mcepacientes.entities.documentos.Documentos;
import it.sup.mcepacientes.repository.documentos.DocumentosRepository;
import it.sup.mcepacientes.services.interfaces.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentosService implements CrudService<DocumentosRequestDTO, Long, DocumentosResponseDTO> {


    private final DocumentosRepository repository;

    public DocumentosService(DocumentosRepository repository) {
        this.repository = repository;
    }

    @Override
    public DocumentosResponseDTO findById(Long aLong) throws Exception {
        return this.repository.findById(aLong)
                .map(Documentos::responseDTO)
                .orElseThrow(Exception::new);
    }

    @Override
    public List<DocumentosResponseDTO> findAll() {
        List<DocumentosResponseDTO> responseDTOS = new ArrayList<>();
        List<Documentos> all = this.repository.findAll();
        if (all.size() > 0) {
            all.parallelStream().forEach(documentos -> {
                responseDTOS.add(Documentos.responseDTO(documentos));
            });
        } else {
            return responseDTOS;
        }
        return responseDTOS;
    }

    @Override
    public Page<DocumentosResponseDTO> findAllPageable(Pageable pageable) {
        List<DocumentosResponseDTO> all = this.findAll();
        return new PageImpl<>(all, pageable, 20);
    }

    @Override
    public DocumentosResponseDTO save(DocumentosRequestDTO entity) {
        Documentos refactored = Documentos.transform(entity);
        return Documentos.responseDTO(this.repository.save(refactored));
    }

    @Override
    public DocumentosResponseDTO update(Long aLong, DocumentosRequestDTO entity) throws Exception {
        Documentos documentos = this.repository.findById(aLong)
                .map(repository -> {
                    repository.setDataEmissao(entity.getDataEmissao());
                    repository.setDataVencimento(entity.getDataVencimento());
                    repository.setEmissor(entity.getEmissor());
                    repository.setType(entity.getType());
                    repository.setValue(entity.getValue());
                    return this.repository.save(repository);
                }).orElseThrow(Exception::new);
        return Documentos.responseDTO(documentos);
    }

    @Override
    public void delete(Long aLong) {
        this.repository.deleteById(aLong);
    }
}
