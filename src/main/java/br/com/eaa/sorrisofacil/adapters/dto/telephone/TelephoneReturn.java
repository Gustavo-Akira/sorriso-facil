package br.com.eaa.sorrisofacil.adapters.dto.telephone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelephoneReturn {
    private Long id;
    private String number;
    private String ddd;
}
