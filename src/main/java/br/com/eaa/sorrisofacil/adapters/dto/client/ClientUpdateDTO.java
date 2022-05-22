package br.com.eaa.sorrisofacil.adapters.dto.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientUpdateDTO {
    private String name;
    private int age;
}
