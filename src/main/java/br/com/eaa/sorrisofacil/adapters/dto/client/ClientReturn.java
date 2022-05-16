package br.com.eaa.sorrisofacil.adapters.dto.client;

import br.com.eaa.sorrisofacil.adapters.dto.contact.ContactReturn;
import br.com.eaa.sorrisofacil.adapters.dto.schedule.ScheduleReturn;
import br.com.eaa.sorrisofacil.application.domain.Contact;
import br.com.eaa.sorrisofacil.application.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientReturn {
    private Long id;
    private String name;
    private int age;
    private List<ContactReturn> contacts;
    private List<ScheduleReturn> schedules;
}
