package it.sup.mcepacientes.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<E, ID, DTO> {

    DTO findById(ID id);

    List<DTO> findAll();

    Page<DTO> findAllPageable(Pageable pageable);

    DTO save(E entity);

    DTO update(ID id, E entity);

    void delete (ID id);
}
