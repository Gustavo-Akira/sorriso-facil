package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.AddressEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ContactEntity;
import br.com.eaa.sorrisofacil.application.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAddressRepository extends JpaRepository<AddressEntity, Long> {
    Page<AddressEntity> findAllByContact(Pageable pageable, ContactEntity contactEntity);
}
