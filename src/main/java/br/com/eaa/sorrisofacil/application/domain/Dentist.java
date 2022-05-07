package br.com.eaa.sorrisofacil.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dentist extends User{
    private List<Service> services;
    private List<Client> clients;
    private List<Schedule> schedules;
}
