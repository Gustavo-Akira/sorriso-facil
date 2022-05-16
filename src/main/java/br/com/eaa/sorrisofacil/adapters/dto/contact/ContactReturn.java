package br.com.eaa.sorrisofacil.adapters.dto.contact;


import br.com.eaa.sorrisofacil.adapters.dto.address.AddressReturn;
import br.com.eaa.sorrisofacil.adapters.dto.telephone.TelephoneReturn;

import java.util.List;

public class ContactReturn {
    private Long id;
    private AddressReturn addresses;
    private List<TelephoneReturn> telephones;
}
