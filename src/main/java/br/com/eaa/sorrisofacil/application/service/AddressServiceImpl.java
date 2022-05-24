package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.application.domain.Address;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.address.AddressRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.address.AddressServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressServicePort {

    @Autowired
    private AddressRepositoryPort port;

    @Override
    public Address getAddress(Long id) {
        return port.getAddress(id);
    }

    @Override
    public Address insertAddress(Address address) {
        return port.insertAddress(address);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        return port.updateAddress(id,address);
    }

    @Override
    public void removeAddress(Long id) {
        port.removeAddress(id);
    }

    @Override
    public Page<Address> getAddresses(PageInformation pageInformation, Client client) {
        return port.getAddresses(pageInformation,client);
    }
}
