package br.com.eaa.sorrisofacil.adapters.dto.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReturn {
    private Long id;
    private Float price;
    private String name;
}
