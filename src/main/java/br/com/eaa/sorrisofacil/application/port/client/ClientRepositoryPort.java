package br.com.eaa.sorrisofacil.application.port.client;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import org.springframework.data.domain.Page;

public interface ClientRepositoryPort {
    Client getClient(Long id);
    Client updateClient(Long id, Client client);
    Page<Client> getClients(PageInformation pageInformation, Dentist dentist);
    void deleteClient(Long id);
    Client insertClient(Client client);
}
