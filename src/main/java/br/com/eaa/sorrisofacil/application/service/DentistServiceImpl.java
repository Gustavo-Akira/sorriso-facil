package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.dentist.DentistRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.dentist.DentistServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Service
public class DentistServiceImpl implements DentistServicePort {

    @Autowired
    private DentistRepositoryPort port;

    @Override
    public Dentist insert(Dentist dentist) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return port.insert(dentist);
    }

    @Override
    public Dentist update(Long id, Dentist dentist) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return port.update(id, dentist);
    }

    @Override
    public Dentist getDentist(Long id) {
        return port.getDentist(id);
    }

    @Override
    public Page<Dentist> getDentists(PageInformation pageInformation) {
        return port.getDentists(pageInformation);
    }

    @Override
    public void removeDentist(Long id) {
        port.removeDentist(id);
    }
}
