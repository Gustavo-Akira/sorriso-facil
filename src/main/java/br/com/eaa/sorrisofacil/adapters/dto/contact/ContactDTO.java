package br.com.eaa.sorrisofacil.adapters.dto.contact;

import br.com.eaa.sorrisofacil.adapters.dto.address.AddressDTO;
import br.com.eaa.sorrisofacil.adapters.dto.telephone.TelephoneDTO;
import br.com.eaa.sorrisofacil.application.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {
    private Client client;
    private AddressDTO addresses;
    private List<TelephoneDTO> telephones;
}
