package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ServiceEntity;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataServiceRepository extends JpaRepository<ServiceEntity, Long> {
    Page<ServiceEntity> findAllByDentist(Pageable pageable, DentistEntity dentist);
}
