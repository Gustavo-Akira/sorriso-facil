package br.com.eaa.sorrisofacil.adapters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorUpdateDTO {
    private String email;
    private String password;
    private String name;
}
