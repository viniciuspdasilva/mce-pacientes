package it.sup.mcepacientes.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Calendar;

@MappedSuperclass
@Getter
@Setter
public class SupperMappedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreationTimestamp
    private Calendar createTimestamp;
    @UpdateTimestamp
    private Calendar updateTimestamp;

    public SupperMappedEntity() {
    }

    @PrePersist
    public void prePersist() {
        this.createTimestamp = Calendar.getInstance();

    }


}
