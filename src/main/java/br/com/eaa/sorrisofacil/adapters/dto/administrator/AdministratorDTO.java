package br.com.eaa.sorrisofacil.adapters.dto.administrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorDTO {
    private String email;
    private String password;
    private String name;
}
