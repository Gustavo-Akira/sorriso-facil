package br.com.eaa.sorrisofacil.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "dentist")
public class Client {
    private Long id;
    private String name;
    private int age;
    private Contact contacts;
    private List<Schedule> schedules;
    private Dentist dentist;
}
