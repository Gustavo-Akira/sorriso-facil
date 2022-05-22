package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.application.domain.Contact;
import br.com.eaa.sorrisofacil.application.port.contact.ContactRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.contact.ContactServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactServicePort {
    @Autowired
    private ContactRepositoryPort port;

    @Override
    public Contact insertContact(Contact contact) {
        return port.insertContact(contact);
    }
}
