package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ClientEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataClientRepository extends JpaRepository<ClientEntity, Long> {
    Page<ClientEntity> findAllByDentist(Pageable pageable, DentistEntity entity);
}
