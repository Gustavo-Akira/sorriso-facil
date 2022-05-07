package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.AdministratorEntity;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.AdministratorRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class AdministratorRepository implements AdministratorRepositoryPort {

    private final SpringDataAdministratorRepository repository;

    private final ModelMapper mapper;

    public AdministratorRepository(ModelMapper mapper, SpringDataAdministratorRepository repository){
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public Administrator insert(Administrator administrator) {
        return mapper.map(repository.save(mapper.map(administrator, AdministratorEntity.class)),Administrator.class);
    }

    @Override
    public Administrator update(Long id, Administrator administrator) {
        administrator.setId(id);
        return mapper.map(repository.save(mapper.map(administrator, AdministratorEntity.class)),Administrator.class);
    }

    @Override
    public Administrator getAdministrator(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(), Administrator.class);
    }

    @Override
    public Page<Administrator> getAdministrators(PageInformation pageInformation) {
        return repository.findAll(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage())).map(x->mapper.map(x,Administrator.class));
    }

    @Override
    public void removeAdministrator(Long id) {
        repository.deleteById(id);
    }
}
