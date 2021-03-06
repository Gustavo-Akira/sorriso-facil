package br.com.eaa.sorrisofacil.adapters.dto.schedule;

import br.com.eaa.sorrisofacil.adapters.dto.client.ClientReturn;
import br.com.eaa.sorrisofacil.adapters.dto.dentist.DentistReturn;
import br.com.eaa.sorrisofacil.adapters.dto.service.ServiceReturn;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleReturn {
    private Long id;
    private ServiceReturn service;
    private ClientReturn client;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
