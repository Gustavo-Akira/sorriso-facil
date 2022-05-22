package br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities;

import br.com.eaa.sorrisofacil.application.domain.Contact;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "client")
@ToString(exclude = "dentist")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @OneToOne(targetEntity = ContactEntity.class)
    private ContactEntity contacts;
    @OneToMany
    private List<ScheduleEntity> schedules;
    @ManyToOne
    @JsonBackReference
    private DentistEntity dentist;
}
