package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ScheduleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface SpringDataScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    Page<ScheduleEntity> findAllByDentist(Pageable pageable, DentistEntity dentistEntity);
    @Transactional
    @Modifying
    @Query("DELETE FROM ScheduleEntity s WHERE s.id=?1")
    void removeSchedule(Long id);
}
