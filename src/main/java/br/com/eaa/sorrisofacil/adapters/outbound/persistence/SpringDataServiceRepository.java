package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ServiceEntity;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SpringDataServiceRepository extends JpaRepository<ServiceEntity, Long> {
    Page<ServiceEntity> findAllByDentist(Pageable pageable, DentistEntity dentist);
    @Modifying
    @Query("delete from ServiceEntity s where s.id=:id")
    void deleteById(Long id);
}
