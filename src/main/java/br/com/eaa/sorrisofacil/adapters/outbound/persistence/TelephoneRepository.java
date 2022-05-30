package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ContactEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.TelephoneEntity;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Telephone;
import br.com.eaa.sorrisofacil.application.port.telephone.TelephoneRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class TelephoneRepository implements TelephoneRepositoryPort {

    @Autowired
    private SpringDataTelephoneRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Telephone getTelephone(Long id) {
        return mapper.map(repository.findById(id),Telephone.class);
    }

    @Override
    public Telephone insertTelephone(Telephone telephone) {
        return mapper.map(repository.save(mapper.map(telephone, TelephoneEntity.class)),Telephone.class);
    }

    @Override
    public Telephone updateTelephone(Long id, Telephone telephone) {
        Telephone old = mapper.map(repository.findById(id).orElseThrow(), Telephone.class);
        if(telephone.getDdd() != null){
            old.setDdd(telephone.getDdd());
        }
        if(telephone.getNumber() != null){
            old.setNumber(telephone.getNumber());
        }
        return mapper.map(repository.save(mapper.map(old,TelephoneEntity.class)),Telephone.class);
    }

    @Override
    public void removeTelephone(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Telephone> getTelephones(PageInformation pageInformation, Client client) {
        return repository.findAllByContact(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage()), mapper.map(client.getContacts(), ContactEntity.class)).map(x->mapper.map(x, Telephone.class));
    }
}
