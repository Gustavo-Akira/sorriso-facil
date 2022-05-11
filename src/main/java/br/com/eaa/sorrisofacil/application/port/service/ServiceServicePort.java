package br.com.eaa.sorrisofacil.application.port.service;

import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Service;
import org.springframework.data.domain.Page;

public interface ServiceServicePort {
    Service insert(Service service);
    Service update(Long id, Service service, Dentist dentist);
    Service getService(Long id, Dentist dentist);
    Page<Service> getServices(PageInformation pageInformation, Dentist dentist);
    void deleteService(Long id, Dentist dentist);
}
