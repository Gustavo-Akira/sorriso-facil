package br.com.eaa.sorrisofacil.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    private Long id;
    private String name;
    private BigDecimal price;
    private Dentist dentist;
    private List<Schedule> schedules;
}
