package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.service.ServiceDTO;
import br.com.eaa.sorrisofacil.adapters.dto.service.ServiceReturn;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Service;
import br.com.eaa.sorrisofacil.application.domain.User;
import br.com.eaa.sorrisofacil.application.port.dentist.DentistServicePort;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import br.com.eaa.sorrisofacil.application.port.service.ServiceServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ServiceController {
    @Autowired
    private ServiceServicePort port;

    @Autowired
    private SecurityUtil util;

    @Autowired
    private LoginServicePort loginServicePort;

    @Autowired
    private ModelMapper mapper;


    @GetMapping("services")
    public Page<ServiceReturn> services(HttpServletRequest request){
        util.dentistAuthorized(request,false);
        return port.getServices(new PageInformation(5,0), loginServicePort.getDentist(request.getHeader("Authorization"))).map(x->mapper.map(x,ServiceReturn.class));
    }

    @PostMapping("service")
    public ServiceReturn service(HttpServletRequest request, @RequestBody @Valid ServiceDTO serviceDTO){
        util.dentistAuthorized(request, false);
        serviceDTO.setDentist(loginServicePort.getDentist(request.getHeader("Authorization")));
        return mapper.map(port.insert(mapper.map(serviceDTO, Service.class)),ServiceReturn.class);
    }
}
