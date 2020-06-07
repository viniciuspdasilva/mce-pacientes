package it.sup.mcepacientes.repository.localizacao;

import it.sup.mcepacientes.entities.localizacao.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
