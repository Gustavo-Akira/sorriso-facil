package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.schedule.ScheduleReturn;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.Schedule;
import br.com.eaa.sorrisofacil.application.domain.Service;
import br.com.eaa.sorrisofacil.application.port.client.ClientServicePort;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import br.com.eaa.sorrisofacil.application.port.schedule.ScheduleServicePort;
import br.com.eaa.sorrisofacil.application.port.service.ServiceServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleServicePort port;

    @Autowired
    private LoginServicePort loginServicePort;

    @Autowired
    private SecurityUtil util;

    @Autowired
    private ClientServicePort clientServicePort;

    @Autowired
    private ServiceServicePort serviceServicePort;

    @GetMapping("schedules")
    public ScheduleReturn getSchedules(HttpServletRequest request){
        util.dentistAuthorized(request);
    return  null;
    }

    private boolean isLoggedDentist(HttpServletRequest request, Long clientId, Long serviceId){
        Client client = clientServicePort.getClient(clientId);
        Service service = serviceServicePort.getService(serviceId);
        Dentist dentist = loginServicePort.getDentist(request.getHeader("Authorization"));
        return client.getDentist().equals(dentist) && service.getDentist().equals(dentist);
    }
}
