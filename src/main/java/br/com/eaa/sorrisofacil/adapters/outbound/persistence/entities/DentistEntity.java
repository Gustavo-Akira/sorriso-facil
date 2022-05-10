package br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Schedule;
import br.com.eaa.sorrisofacil.application.domain.Service;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DentistEntity extends  UserEntity{
    @OneToMany(mappedBy = "dentist", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<ServiceEntity> services;
    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ClientEntity> clients;
    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ScheduleEntity> schedules;
}
