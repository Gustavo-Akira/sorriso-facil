package br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Float price;
    @OneToMany(mappedBy = "service")
    private List<ScheduleEntity> schedules;
    @ManyToOne
    private DentistEntity dentist;
}
