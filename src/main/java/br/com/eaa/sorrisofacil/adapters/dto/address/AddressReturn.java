package br.com.eaa.sorrisofacil.adapters.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressReturn {
    private Long id;
    private String street;
    private int number;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String cep;
}
