package br.com.eaa.sorrisofacil.application.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "dentist")
@EqualsAndHashCode(exclude = "dentist")
public class Schedule {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Service service;
    private Client client;
    private Dentist dentist;
}
