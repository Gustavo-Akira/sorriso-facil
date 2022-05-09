package br.com.eaa.sorrisofacil.adapters.dto.dentist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentistUpdateDTO {
    @Email
    @Nullable
    private String email;
    private String name;
    private String password;
}
