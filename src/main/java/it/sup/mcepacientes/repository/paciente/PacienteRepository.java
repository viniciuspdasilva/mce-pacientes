package it.sup.mcepacientes.repository.paciente;

import it.sup.mcepacientes.entities.pacientes.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
