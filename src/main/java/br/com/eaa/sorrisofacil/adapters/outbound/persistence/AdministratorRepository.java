package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.AdministratorEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.util.PasswordUtil;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.administrator.AdministratorRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Repository
public class AdministratorRepository implements AdministratorRepositoryPort {

    private final SpringDataAdministratorRepository repository;

    private final ModelMapper mapper;

    private final PasswordUtil util;


    public AdministratorRepository(ModelMapper mapper, SpringDataAdministratorRepository repository, PasswordUtil util){
        this.mapper = mapper;
        this.repository = repository;
        this.util = util;
    }

    @Override
    public Administrator insert(Administrator administrator) throws NoSuchAlgorithmException, InvalidKeySpecException {
        administrator.setPassword(util.encode(administrator.getPassword()));
        return mapper.map(repository.save(mapper.map(administrator, AdministratorEntity.class)),Administrator.class);
    }

    @Override
    public Administrator update(Long id, Administrator administrator) throws NoSuchAlgorithmException, InvalidKeySpecException {
        administrator.setId(id);
        Administrator oldAdministrator = getAdministrator(id);
        if(administrator.getPassword() != null){
            oldAdministrator.setPassword(util.encode(administrator.getPassword()));
        }
        if(administrator.getName() != null){
            oldAdministrator.setName(administrator.getName());
        }
        if(administrator.getEmail() != null){
            oldAdministrator.setEmail(administrator.getEmail());
        }
        return mapper.map(repository.save(mapper.map(oldAdministrator, AdministratorEntity.class)),Administrator.class);
    }

    @Override
    public Administrator getAdministrator(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(() -> new NotFoundElementException("Administrator with this id not found")), Administrator.class);
    }

    @Override
    public Page<Administrator> getAdministrators(PageInformation pageInformation) {
        return repository.findAll(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage())).map(x->mapper.map(x,Administrator.class));
    }

    @Override
    public void removeAdministrator(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Administrator getAdministratorByEmailAndPassword(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Optional<AdministratorEntity> entity = repository.findByEmail(email);
        if(!entity.isEmpty() && util.validatePassword(password,entity.get().getPassword())){
            return mapper.map(entity.get(), Administrator.class);
        }
        return null;
    }

    @Override
    public Administrator getAdministratorByEmail(String email) {
        Optional<AdministratorEntity> entity = repository.findByEmail(email);
        if(!entity.isEmpty()){
            return mapper.map(entity.get(), Administrator.class);
        }
        return null;
    }
}
