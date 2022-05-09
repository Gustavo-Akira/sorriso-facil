package br.com.eaa.sorrisofacil.adapters.dto.administrator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorUpdateDTO {
    @Email(message = "The email is invalid")
    @Nullable
    private String email;
    private String password;
    private String name;
}
