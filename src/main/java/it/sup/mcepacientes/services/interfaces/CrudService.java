package it.sup.mcepacientes.services.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CrudService<E, ID> {

    E findById(ID id);

    List<E> findAll();

    Page<E> findAllPageable(Pageable pageable);

    E save(E entity);

    E update(ID id, E entity);

    void delete (ID id);
}
