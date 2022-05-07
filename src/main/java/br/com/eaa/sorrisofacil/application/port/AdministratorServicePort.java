package br.com.eaa.sorrisofacil.application.port;

import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface AdministratorServicePort {
    Administrator insert(Administrator administrator);
    Administrator update(Long id, Administrator administrator);
    Administrator getAdministrator(Long id);
    Page<Administrator> getAdministrators(PageInformation pageInformation);
    void removeAdministrator(Long id);
}
