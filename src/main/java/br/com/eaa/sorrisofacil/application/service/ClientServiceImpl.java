package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.client.ClientRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.client.ClientServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientServicePort {

    @Autowired
    private ClientRepositoryPort port;

    @Override
    public Client getClient(Long id) {
        return port.getClient(id);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        return port.updateClient(id,client);
    }

    @Override
    public Page<Client> getClients(PageInformation pageInformation, Dentist dentist) {
        return port.getClients(pageInformation, dentist);
    }

    @Override
    public void deleteClient(Long id) {
        port.deleteClient(id);
    }

    @Override
    public Client insertClient(Client client) {
        return port.insertClient(client);
    }
}
