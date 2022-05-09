package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.AdministratorRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.AdministratorServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

@Service
public class AdministratorServiceImpl implements AdministratorServicePort {
    @Autowired
    private AdministratorRepositoryPort port;

    @Override
    public Administrator insert(Administrator administrator) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return port.insert(administrator);
    }

    @Override
    public Administrator update(Long id, Administrator administrator) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return port.update(id, administrator);
    }

    @Override
    public Administrator getAdministrator(Long id) {
        return port.getAdministrator(id);
    }

    @Override
    public Page<Administrator> getAdministrators(PageInformation pageInformation) {
        return port.getAdministrators(pageInformation);
    }

    @Override
    public void removeAdministrator(Long id) {
        port.removeAdministrator(id);
    }
}
