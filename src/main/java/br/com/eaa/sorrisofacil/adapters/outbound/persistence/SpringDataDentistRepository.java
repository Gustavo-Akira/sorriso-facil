package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataDentistRepository extends JpaRepository<DentistEntity, Long> {
    DentistEntity findByEmailAndPassword(String email, String password);

    DentistEntity findByEmail(String email);
}
