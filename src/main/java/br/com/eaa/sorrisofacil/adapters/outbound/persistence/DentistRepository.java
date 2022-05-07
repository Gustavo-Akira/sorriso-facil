package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.DentistRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class DentistRepository implements DentistRepositoryPort {

    @Autowired
    private SpringDataDentistRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Dentist insert(Dentist dentist) {
        return mapper.map(repository.save(mapper.map(dentist, DentistEntity.class)),Dentist.class);
    }

    @Override
    public Dentist update(Long id, Dentist dentist) {
        dentist.setId(id);
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
}
