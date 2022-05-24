package br.com.eaa.sorrisofacil.adapters.dto.address;

import br.com.eaa.sorrisofacil.application.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @NotEmpty(message = "street cannot be empty")
    @NotNull(message = "street cannot be null")
    private String street;
    private int number;
    @NotEmpty(message = "neighborhood cannot be empty")
    @NotNull(message = "neighborhood cannot be null")
    private String neighborhood;
    @NotEmpty(message = "city cannot be empty")
    @NotNull(message = "city cannot be null")
    private String city;
    @NotEmpty(message = "state cannot be empty")
    @NotNull(message = "state cannot be null")
    private String state;
    @NotEmpty(message = "country cannot be empty")
    @NotNull(message = "country cannot be null")
    private String country;
    @NotEmpty(message = "cep cannot be empty")
    @NotNull(message = "cep cannot be null")
    private String cep;
    private Contact contact;
}
