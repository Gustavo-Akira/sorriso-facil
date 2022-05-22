package br.com.eaa.sorrisofacil.application.port.contact;

import br.com.eaa.sorrisofacil.application.domain.Contact;

public interface ContactRepositoryPort {
    Contact insertContact(Contact contact);
}
