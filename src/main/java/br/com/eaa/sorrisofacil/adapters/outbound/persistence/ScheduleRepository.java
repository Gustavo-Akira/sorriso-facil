package br.com.eaa.sorrisofacil.adapters.outbound.persistence;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.DentistEntity;
import br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities.ScheduleEntity;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Schedule;
import br.com.eaa.sorrisofacil.application.port.schedule.ScheduleRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository implements ScheduleRepositoryPort {

    @Autowired
    private SpringDataScheduleRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Schedule getSchedule(Long id) {
        return mapper.map(repository.findById(id).orElseThrow(()->new NotFoundElementException("Schedule not found")), Schedule.class);
    }

    @Override
    public Page<Schedule> getSchedules(PageInformation pageInformation, Dentist dentist) {
        return repository.findAllByDentist(Pageable.ofSize(pageInformation.getPageSize()).withPage(pageInformation.getActualPage()),mapper.map(dentist, DentistEntity.class)).map(x->mapper.map(x,Schedule.class));
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        Schedule old = mapper.map(repository.getById(id), Schedule.class);
        if(schedule.getService() != null){
            old.setService(schedule.getService());
        }
        if(schedule.getStartTime() != null){
            old.setStartTime(schedule.getStartTime());
        }
        if(schedule.getEndTime() != null){
            old.setEndTime(schedule.getEndTime());
        }
        return mapper.map(repository.save(mapper.map(schedule,ScheduleEntity.class)),Schedule.class);
    }

    @Override
    public Schedule insertSchedule(Schedule schedule) {
        return mapper.map(repository.save(mapper.map(schedule,ScheduleEntity.class)),Schedule.class);
    }

    @Override
    public void removeSchedule(Long id) {
        repository.deleteById(id);
    }
}
