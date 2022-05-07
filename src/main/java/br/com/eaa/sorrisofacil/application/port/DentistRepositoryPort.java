package br.com.eaa.sorrisofacil.application.port;

import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import org.springframework.data.domain.Page;

public interface DentistRepositoryPort {
    Dentist insert(Dentist dentist);
    Dentist update(Long id, Dentist dentist);
    Dentist getDentist(Long id);
    Page<Dentist> getDentists(PageInformation pageInformation);
    void removeDentist(Long id);
}
