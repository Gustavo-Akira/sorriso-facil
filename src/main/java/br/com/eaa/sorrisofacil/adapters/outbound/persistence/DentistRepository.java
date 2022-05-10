package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.util.PasswordUtil;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.dentist.DentistRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Repository
public class DentistRepository implements DentistRepositoryPort {

    @Autowired
    private SpringDataDentistRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordUtil util;


    @Override
    public Dentist insert(Dentist dentist) throws NoSuchAlgorithmException, InvalidKeySpecException {
        dentist.setPassword(util.encode(dentist.getPassword()));
        return mapper.map(repository.save(mapper.map(dentist, DentistEntity.class)),Dentist.class);
    }

    @Override
    public Dentist update(Long id, Dentist dentist) throws NoSuchAlgorithmException, InvalidKeySpecException {
        dentist.setId(id);
        Dentist oldDentist = getDentist(id);
        if(dentist.getPassword() != null){

            oldDentist.setPassword(util.encode(dentist.getPassword()));
        }
        if(dentist.getEmail() != null){
            oldDentist.setEmail(dentist.getEmail());
        }
        if(dentist.getName() != null){
            oldDentist.setName(dentist.getName());
        }
        return mapper.map(repository.save(mapper.map(oldDentist, DentistEntity.class)),Dentist.class);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Dentist getDentist(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(() -> new NotFoundElementException("Dentist with this id not found")),Dentist.class);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Page<Dentist> getDentists(PageInformation pageInformation) {
        return repository.findAll(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage())).map(x->mapper.map(x,Dentist.class));
    }

    @Override
    public void removeDentist(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Dentist getDentistByEmailAndPassword(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException, LoginException {
        DentistEntity entity = repository.findByEmail(email);
        if(entity != null && util.validatePassword(password, entity.getPassword())){
            return mapper.map(entity,Dentist.class);
        }
        return null;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Dentist getDentistByEmail(String email) {
        DentistEntity entity = repository.findByEmail(email);
        if(entity != null){
            return mapper.map(entity,Dentist.class);
        }
        return null;
    }
}
