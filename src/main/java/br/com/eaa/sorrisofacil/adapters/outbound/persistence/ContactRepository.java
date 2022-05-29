package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ContactEntity;
import br.com.eaa.sorrisofacil.application.domain.Contact;
import br.com.eaa.sorrisofacil.application.port.contact.ContactRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository implements ContactRepositoryPort {

    @Autowired
    private SpringDataContactRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Contact insertContact(Contact contact) {
        return mapper.map(repository.save(mapper.map(contact, ContactEntity.class)),Contact.class);
    }

    public void removeContact(Long id){
        repository.deleteById(id);
    }
}
