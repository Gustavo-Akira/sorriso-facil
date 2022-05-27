package br.com.eaa.sorrisofacil.adapters.dto.schedule;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleDTO {
    @NotNull(message = "StartTime cannot be null")
    private LocalDateTime startTime;
    @NotNull(message = "EndTime cannot be null")
    private LocalDateTime endTime;
    @NotNull(message = "Service cannot be null")
    private Service service;
    private Client client;
    private Dentist dentist;
}
