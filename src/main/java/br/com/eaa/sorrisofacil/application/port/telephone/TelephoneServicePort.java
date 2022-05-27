package br.com.eaa.sorrisofacil.application.port.telephone;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Telephone;
import org.springframework.data.domain.Page;

public interface TelephoneServicePort {
    Telephone getTelephone(Long id);
    Telephone insertTelephone(Telephone telephone);
    Telephone updateTelephone(Long id, Telephone telephone);
    void removeTelephone(Long id);
    Page<Telephone> getTelephones(PageInformation pageInformation, Client client);
}
