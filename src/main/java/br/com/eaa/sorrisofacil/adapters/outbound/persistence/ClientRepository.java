package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ClientEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ContactEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.application.domain.*;
import br.com.eaa.sorrisofacil.application.port.client.ClientRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;
@Repository
public class ClientRepository implements ClientRepositoryPort {

    @Autowired
    private SpringDataClientRepository repository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private SpringDataAddressRepository addressRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Client getClient(Long id) {
        Client client = mapper.map(repository.findById(id).orElseThrow(()->new NotFoundElementException("client not found")), Client.class);
        client.getContacts().setAddress(mapper.map(addressRepository.findAllByContact(Pageable.ofSize(5).withPage(0),mapper.map(client.getContacts(), ContactEntity.class)).stream().findFirst().get(), Address.class));
        return client;
    }

    @Override
    public Client updateClient(Long id, Client client) {
        Client old = mapper.map(repository.getById(id),Client.class);
        if(client.getName() != null){
            old.setName(client.getName());
        }
        if(client.getAge() != 0){
            old.setAge(client.getAge());
        }
        if(client.getContacts() != null){
            old.setContacts(client.getContacts());
        }
        return mapper.map(repository.save(mapper.map(old, ClientEntity.class)),Client.class);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Page<Client> getClients(PageInformation pageInformation, Dentist dentist) {
        return repository.findAllByDentist(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage()), mapper.map(dentist, DentistEntity.class)).map(x->mapper.map(x,Client.class));
    }

    @Override
    public void deleteClient(Long id) {
        ClientEntity entity = repository.getById(id);
        Long contactId = 0L;
        if(entity.getContacts() != null) {
            contactId = entity.getContacts().getId();
        }
        entity.setContacts(null);
        entity = repository.save(entity);
        if(repository.findById(entity.getId()).isPresent()) {
            repository.deleteById(entity.getId());
        }
    }

    @Override
    public Client insertClient(Client client) {
        Client entity = mapper.map(repository.save(mapper.map(client,ClientEntity.class)),Client.class);
        Contact contact = new Contact();
        contact.setClient(entity);
        contact = contactRepository.insertContact(contact);
        entity.setContacts(contact);
        return mapper.map(repository.save(mapper.map(entity, ClientEntity.class)),Client.class);
    }
}
