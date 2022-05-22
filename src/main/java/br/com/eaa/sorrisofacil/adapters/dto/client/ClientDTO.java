package br.com.eaa.sorrisofacil.adapters.dto.client;

import br.com.eaa.sorrisofacil.application.domain.Contact;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private int age;
    private Dentist dentist;
}
