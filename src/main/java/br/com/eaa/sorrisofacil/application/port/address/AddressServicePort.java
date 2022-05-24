package br.com.eaa.sorrisofacil.application.port.address;

import br.com.eaa.sorrisofacil.application.domain.Address;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import org.springframework.data.domain.Page;

public interface AddressServicePort {
    Address getAddress(Long id);
    Address insertAddress(Address address);
    Address updateAddress(Long id, Address address);
    void removeAddress(Long id);
    Page<Address> getAddresses(PageInformation pageInformation, Client client);
}
