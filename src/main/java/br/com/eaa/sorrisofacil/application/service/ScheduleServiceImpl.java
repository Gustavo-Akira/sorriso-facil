package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Schedule;
import br.com.eaa.sorrisofacil.application.port.schedule.ScheduleRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.schedule.ScheduleServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ScheduleServiceImpl implements ScheduleServicePort {

    @Autowired
    private ScheduleRepositoryPort port;

    @Override
    public Schedule getSchedule(Long id) {
        return port.getSchedule(id);
    }

    @Override
    public Page<Schedule> getSchedules(PageInformation pageInformation, Dentist dentist) {
        return port.getSchedules(pageInformation, dentist);
    }

    @Override
    public Schedule updateSchedule(Long id, Schedule schedule) {
        return port.updateSchedule(id,schedule);
    }

    @Override
    public Schedule insertSchedule(Schedule schedule) {
        return port.insertSchedule(schedule);
    }

    @Override
    public void removeSchedule(Long id) {
        port.removeSchedule(id);
    }
}
