package br.com.eaa.sorrisofacil.adapters.dto.contact;


import br.com.eaa.sorrisofacil.adapters.dto.address.AddressReturn;
import br.com.eaa.sorrisofacil.adapters.dto.client.ClientReturn;
import br.com.eaa.sorrisofacil.adapters.dto.telephone.TelephoneReturn;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "client")
public class ContactReturn {
    private Long id;
    private AddressReturn addresses;
    @JsonIgnore
    private ClientReturn client;
    private List<TelephoneReturn> telephones;
}
