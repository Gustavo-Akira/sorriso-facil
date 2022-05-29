package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.schedule.ScheduleDTO;
import br.com.eaa.sorrisofacil.adapters.dto.schedule.ScheduleReturn;
import br.com.eaa.sorrisofacil.adapters.dto.schedule.ScheduleUpdateDTO;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.*;
import br.com.eaa.sorrisofacil.application.port.client.ClientServicePort;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import br.com.eaa.sorrisofacil.application.port.schedule.ScheduleServicePort;
import br.com.eaa.sorrisofacil.application.port.service.ServiceServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @Autowired
    private ModelMapper mapper;

    @GetMapping("schedules")
    public Page<ScheduleReturn> getSchedules(HttpServletRequest request){
        util.dentistAuthorized(request);

        return  port.getSchedules(new PageInformation(5,0), loginServicePort.getDentist(request.getHeader("Authorization"))).map(x->mapper.map(x, ScheduleReturn.class));
    }

    @GetMapping("schedules/{page}")
    public Page<ScheduleReturn> getSchedules(HttpServletRequest request, @PathVariable Integer page){
        util.dentistAuthorized(request);

        return  port.getSchedules(new PageInformation(5,page), loginServicePort.getDentist(request.getHeader("Authorization"))).map(x->mapper.map(x, ScheduleReturn.class));
    }

    @PostMapping("schedule/client/{client}/service/{service}")
    public ScheduleReturn saveSchedule(HttpServletRequest request, @RequestBody @Valid ScheduleDTO scheduleDTO, @PathVariable(name = "client") Long client, @PathVariable(name = "service") Long service){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request,client, service)){
            throw new LoginException("Unauthorized");
        }
        scheduleDTO.setClient(clientServicePort.getClient(client));
        scheduleDTO.setDentist(loginServicePort.getDentist(request.getHeader("Authorization")));
        scheduleDTO.setService(serviceServicePort.getService(service));
        return mapper.map(port.insertSchedule(mapper.map(scheduleDTO,Schedule.class)),ScheduleReturn.class);
    }

    @PutMapping("schedule/{id}")
    public ScheduleReturn updateSchedule(HttpServletRequest request, @PathVariable Long id,@RequestBody @Valid ScheduleUpdateDTO dto){
        util.dentistAuthorized(request);
        if(!isScheduleOfDentinst(id,request)){
            throw new LoginException("Unauthorized");
        }
        if(dto.getServiceId() != null && !serviceServicePort.getService(id).getDentist().equals(loginServicePort.getDentist(request.getHeader("Authorization")))){
            throw new LoginException("Unauthorized");
        }
        
        return mapper.map(port.updateSchedule(id,mapper.map(dto,Schedule.class)),ScheduleReturn.class);
    }

    @DeleteMapping("schedule/{id}")
    public ResponseEntity<Void> deleteSchedule(HttpServletRequest request, @PathVariable Long id){
        util.dentistAuthorized(request);
        if(!isScheduleOfDentinst(id,request)){
            throw new LoginException("Unauthorized");
        }
        port.removeSchedule(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isScheduleOfDentinst(Long id, HttpServletRequest request){
        return port.getSchedule(id).getDentist().getId().equals(loginServicePort.getDentist(request.getHeader("Authorization")).getId());
    }

    private boolean isLoggedDentist(HttpServletRequest request, Long clientId, Long serviceId){
        Client client = clientServicePort.getClient(clientId);
        Service service = serviceServicePort.getService(serviceId);
        Dentist dentist = loginServicePort.getDentist(request.getHeader("Authorization"));
        return client.getDentist().equals(dentist) && service.getDentist().equals(dentist);
    }
}
