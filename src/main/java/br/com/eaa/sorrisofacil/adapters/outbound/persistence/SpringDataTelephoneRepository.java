package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ContactEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.TelephoneEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataTelephoneRepository extends JpaRepository<TelephoneEntity, Long> {
    Page<TelephoneEntity> findAllByContact(Pageable pageable, ContactEntity contact);
}
