package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.AddressEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ContactEntity;
import br.com.eaa.sorrisofacil.application.domain.Address;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.address.AddressRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepository implements AddressRepositoryPort {

    @Autowired
    private SpringDataAddressRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Address getAddress(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(()-> new NotFoundElementException("Address Not Found")), Address.class);
    }

    @Override
    public Address insertAddress(Address address) {
        return mapper.map(repository.save(mapper.map(address, AddressEntity.class)),Address.class);
    }

    @Override
    public Address updateAddress(Long id, Address address) {
        address.setId(id);
        return mapper.map(repository.save(mapper.map(address, AddressEntity.class)),Address.class);
    }

    @Override
    public void removeAddress(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Address> getAddresses(PageInformation pageInformation, Client client) {
        return repository.findAllByContact(Pageable.ofSize(5).withPage(pageInformation.getActualPage()),mapper.map(client.getContacts(), ContactEntity.class)).map(x-> mapper.map(x, Address.class));
    }
}