package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataContactRepository extends JpaRepository<ContactEntity, Long> {
}
