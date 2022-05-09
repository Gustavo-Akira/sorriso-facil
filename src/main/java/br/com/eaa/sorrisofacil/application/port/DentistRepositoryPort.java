package br.com.eaa.sorrisofacil.application.port;

import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import org.springframework.data.domain.Page;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface DentistRepositoryPort {
    Dentist insert(Dentist dentist) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Dentist update(Long id, Dentist dentist) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Dentist getDentist(Long id);
    Page<Dentist> getDentists(PageInformation pageInformation);
    void removeDentist(Long id);
    Dentist getDentistByEmailAndPassword(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    Dentist getDentistByEmail(String email);
}
