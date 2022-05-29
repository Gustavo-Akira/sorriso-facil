package br.com.eaa.sorrisofacil.adapters.dto.schedule;

import br.com.eaa.sorrisofacil.application.domain.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleUpdateDTO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Service service;
    private Long serviceId;
}
