package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.security.PasswordUtil;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.DentistRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
        if(!dentist.getPassword().contains("1000")){
            dentist.setPassword(util.encode(dentist.getPassword()));
        }
        return mapper.map(repository.save(mapper.map(dentist, DentistEntity.class)),Dentist.class);
    }

    @Override
    public Dentist getDentist(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(),Dentist.class);
    }

    @Override
    public Page<Dentist> getDentists(PageInformation pageInformation) {
        return repository.findAll(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage())).map(x->mapper.map(x,Dentist.class));
    }

    @Override
    public void removeDentist(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Dentist getDentistByEmailAndPassword(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        DentistEntity entity = repository.findByEmail(email);
        if(entity != null && util.validatePassword(password, entity.getPassword())){
            return mapper.map(entity,Dentist.class);
        }
        return null;
    }

    @Override
    public Dentist getDentistByEmail(String email) {
        DentistEntity entity = repository.findByEmail(email);
        if(entity != null){
            return mapper.map(entity,Dentist.class);
        }
        return null;
    }
}
