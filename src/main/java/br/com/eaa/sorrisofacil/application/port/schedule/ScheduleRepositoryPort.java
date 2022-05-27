package br.com.eaa.sorrisofacil.application.port.schedule;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Schedule;
import org.springframework.data.domain.Page;

public interface ScheduleRepositoryPort {
    Schedule getSchedule(Long id);
    Page<Schedule> getSchedules(PageInformation pageInformation, Dentist dentist);
    Schedule updateSchedule(Long id, Schedule schedule);
    Schedule insertSchedule(Schedule schedule);
    void removeSchedule(Long id);
}
