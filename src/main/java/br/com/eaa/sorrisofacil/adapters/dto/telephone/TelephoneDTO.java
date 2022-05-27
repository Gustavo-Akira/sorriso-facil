package br.com.eaa.sorrisofacil.adapters.dto.telephone;

import br.com.eaa.sorrisofacil.application.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelephoneDTO {
    @NotEmpty(message = "Number cannot be empty")
    @NotNull(message = "Number cannot be null")
    private String number;
    @NotEmpty(message = "Ddd cannot be empty")
    @NotNull(message = "Ddd cannot be null")
    private String ddd;

    private Contact contact;
}
