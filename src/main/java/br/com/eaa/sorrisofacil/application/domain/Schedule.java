package br.com.eaa.sorrisofacil.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Service service;
    private Client client;
    private Dentist dentist;
}
