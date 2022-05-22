package br.com.eaa.sorrisofacil.adapters.dto.contact;

import br.com.eaa.sorrisofacil.application.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    @NotNull(message = "id cannot be null")
    private Long id;
}
