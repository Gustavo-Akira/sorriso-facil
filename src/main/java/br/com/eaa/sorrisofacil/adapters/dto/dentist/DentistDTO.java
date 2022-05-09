package br.com.eaa.sorrisofacil.adapters.dto.dentist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentistDTO {
    private String email;
    private String password;
    private String name;
}
