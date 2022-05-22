package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ClientEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.client.ClientRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;
@Repository
public class ClientRepository implements ClientRepositoryPort {

    @Autowired
    private SpringDataClientRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Client getClient(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(()->new NotFoundElementException("client not found")), Client.class);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        client.setId(id);
        return mapper.map(repository.save(mapper.map(client, ClientEntity.class)),Client.class);
    }

    @Override
    public Page<Client> getClients(PageInformation pageInformation, Dentist dentist) {
        return repository.findAllByDentist(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage()), mapper.map(dentist, DentistEntity.class)).map(x->mapper.map(x,Client.class));
    }

    @Override
    public void deleteClient(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Client insertClient(Client client) {
        return mapper.map(repository.save(mapper.map(client, ClientEntity.class)),Client.class);
    }
}
