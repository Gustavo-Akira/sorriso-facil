package br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Schedule;
import br.com.eaa.sorrisofacil.application.domain.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DentistEntity extends  UserEntity{
    @OneToMany(mappedBy = "dentist")
    private List<ServiceEntity> services;
    @OneToMany(mappedBy = "dentist")
    private List<ClientEntity> clients;
    @OneToMany(mappedBy = "dentist")
    private List<ScheduleEntity> schedules;
}
