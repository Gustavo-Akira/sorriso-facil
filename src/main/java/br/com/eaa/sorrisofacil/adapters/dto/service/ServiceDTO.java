package br.com.eaa.sorrisofacil.adapters.dto.service;

import br.com.eaa.sorrisofacil.application.domain.Dentist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {

    @Min(1)
    @NotNull(message = "price cannot be null")
    private BigDecimal price;
    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String name;
    private Dentist dentist;
}
