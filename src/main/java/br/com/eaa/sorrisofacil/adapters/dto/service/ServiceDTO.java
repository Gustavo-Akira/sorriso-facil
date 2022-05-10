package br.com.eaa.sorrisofacil.adapters.dto.service;

import br.com.eaa.sorrisofacil.application.domain.Dentist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Float price;
    private String name;
    private Dentist dentist;
}
