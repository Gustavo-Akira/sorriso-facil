package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ServiceEntity;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Service;
import br.com.eaa.sorrisofacil.application.port.service.ServiceRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRepository implements ServiceRepositoryPort {

    @Autowired
    private SpringDataServiceRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Service insert(Service service) {
        return mapper.map(repository.save(mapper.map(service, ServiceEntity.class)),Service.class);
    }

    @Override
    public Service update(Long id, Service service) {
        Service oldService = getService(id);
        if(service.getName()!= null){
            oldService.setName(service.getName());
        }
        if(service.getPrice() != null){
            oldService.setPrice(service.getPrice());
        }
        return mapper.map(repository.save(mapper.map(oldService,ServiceEntity.class)),Service.class);
    }

    @Override
    public Service getService(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(), Service.class);
    }

    @Override
    public Page<Service> getServices(PageInformation pageInformation, Dentist dentist) {
        return repository.findAllByDentist(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage()),mapper.map(dentist, DentistEntity.class)).map(x->mapper.map(x,Service.class));
    }

    @Override
    public void deleteService(Long id) {
        repository.deleteById(id);
    }
}
