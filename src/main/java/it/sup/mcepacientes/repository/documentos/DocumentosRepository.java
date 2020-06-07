package it.sup.mcepacientes.repository.documentos;

import it.sup.mcepacientes.entities.documentos.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentosRepository extends JpaRepository<Documentos, Long> {


}
