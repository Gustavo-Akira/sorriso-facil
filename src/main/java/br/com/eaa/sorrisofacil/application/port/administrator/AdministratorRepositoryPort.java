package br.com.eaa.sorrisofacil.application.port.administrator;

import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import org.springframework.data.domain.Page;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.UUID;

public interface AdministratorRepositoryPort {
    Administrator insert(Administrator administrator) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Administrator update(Long id, Administrator administrator) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Administrator getAdministrator(Long id);
    Page<Administrator> getAdministrators(PageInformation pageInformation);
    void removeAdministrator(Long id);
    Administrator getAdministratorByEmailAndPassword(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Administrator getAdministratorByEmail(String email);
}
