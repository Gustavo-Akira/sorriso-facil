package br.com.eaa.sorrisofacil.adapters.dto.dentist;

import br.com.eaa.sorrisofacil.adapters.dto.schedule.ScheduleReturn;
import br.com.eaa.sorrisofacil.adapters.dto.service.ServiceReturn;
import br.com.eaa.sorrisofacil.adapters.dto.client.ClientReturn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentistReturn {
    private Long id;
    private String name;
    private String email;
    private List<ClientReturn> clients;
    private List<ScheduleReturn> schedules;
    private List<ServiceReturn> services;
}
