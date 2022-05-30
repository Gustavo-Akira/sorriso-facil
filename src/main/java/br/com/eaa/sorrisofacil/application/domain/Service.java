package br.com.eaa.sorrisofacil.application.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"schedules","dentist"})
@EqualsAndHashCode(exclude = {"schedules","dentist"})
public class Service {
    private Long id;
    private String name;
    private BigDecimal price;
    private Dentist dentist;
    private List<Schedule> schedules;
}
