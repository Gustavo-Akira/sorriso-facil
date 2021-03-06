package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Service;
import br.com.eaa.sorrisofacil.application.port.service.ServiceRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.service.ServiceServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceServicePort {

    @Autowired
    private ServiceRepositoryPort port;

    @Override
    public Service insert(Service service) {
        return port.insert(service);
    }

    @Override
    public Service update(Long id, Service service) {
        return port.update(id, service);
    }

    @Override
    public Service getService(Long id) {
        return port.getService(id);
    }

    @Override
    public Page<Service> getServices(PageInformation pageInformation, Dentist dentist) {
        return port.getServices(pageInformation,dentist);
    }

    @Override
    public void deleteService(Long id) {
        port.deleteService(id);
    }

}
