package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.AdministratorRepository;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.DentistRepository;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.port.administrator.AdministratorRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.dentist.DentistRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.validation.EmailUtilServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailUtilImpl implements EmailUtilServicePort {
    @Autowired
    private AdministratorRepositoryPort repository;

    @Autowired
    private DentistRepositoryPort dentistRepository;

    public boolean validEmail(String email){
        Administrator administrator  = repository.getAdministratorByEmail(email);
        Dentist dentist = dentistRepository.getDentistByEmail(email);
        if(administrator != null || dentist != null){
            return false;
        }
        return true;
    }
}
