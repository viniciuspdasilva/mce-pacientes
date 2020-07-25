package it.sup.mcepacientes;

import it.sup.mcepacientes.entities.documentos.Documentos;
import it.sup.mcepacientes.entities.localizacao.Endereco;
import it.sup.mcepacientes.entities.pacientes.Paciente;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {
        Paciente.class,
        Endereco.class,
        Documentos.class
})
public class McePacientesApplication {

    public static void main(String[] args) {
        SpringApplication.run(McePacientesApplication.class, args);
    }

}
