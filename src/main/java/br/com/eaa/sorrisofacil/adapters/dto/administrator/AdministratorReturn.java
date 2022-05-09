package br.com.eaa.sorrisofacil.adapters.dto.administrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorReturn {
    private Long id;
    private String name;
    private String email;
}
