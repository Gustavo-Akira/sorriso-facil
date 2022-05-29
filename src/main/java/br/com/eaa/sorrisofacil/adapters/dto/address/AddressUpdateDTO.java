package br.com.eaa.sorrisofacil.adapters.dto.address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressUpdateDTO {
    private String street;
    private int number;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String cep;
}
