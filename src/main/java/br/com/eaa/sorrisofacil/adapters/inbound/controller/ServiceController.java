package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.service.ServiceDTO;
import br.com.eaa.sorrisofacil.adapters.dto.service.ServiceReturn;
import br.com.eaa.sorrisofacil.adapters.dto.service.ServiceUpdateDTO;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

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
        util.dentistAuthorized(request);
        return port.getServices(new PageInformation(5,0), loginServicePort.getDentist(request.getHeader("Authorization"))).map(x->mapper.map(x,ServiceReturn.class));
    }

    @GetMapping("services/{page}")
    public Page<ServiceReturn> services(HttpServletRequest request, @PathVariable Integer page){
        util.dentistAuthorized(request);
        return port.getServices(new PageInformation(5,page), loginServicePort.getDentist(request.getHeader("Authorization"))).map(x->mapper.map(x,ServiceReturn.class));
    }

    @PostMapping("service")
    public ResponseEntity<ServiceReturn> insertService(HttpServletRequest request, @RequestBody @Valid ServiceDTO serviceDTO){
        util.dentistAuthorized(request);
        serviceDTO.setDentist(loginServicePort.getDentist(request.getHeader("Authorization")));
        Service service = port.insert(mapper.map(serviceDTO, Service.class));
        return ResponseEntity.created(URI.create("service"+service.getId())).body(mapper.map(service,ServiceReturn.class));
    }

    @GetMapping("service/{id}")
    public ResponseEntity<ServiceReturn> getService(HttpServletRequest request,@PathVariable Long id){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(id, request.getHeader("Authorization"))){
            throw new LoginException("Unauthorized");
        }
        return ResponseEntity.ok().body(mapper.map(port.getService(id),ServiceReturn.class));
    }

    @PutMapping("service/{id}")
    public ResponseEntity<ServiceReturn> updateService(HttpServletRequest request, @PathVariable Long id, @RequestBody @Valid ServiceUpdateDTO serviceDTO){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(id, request.getHeader("Authorization"))){
            throw new LoginException("Unauthorized");
        }
        return ResponseEntity.ok().body(mapper.map(port.update(id,mapper.map(serviceDTO, Service.class)),ServiceReturn.class));
    }
    @DeleteMapping("service/{id}")
    public ResponseEntity<Void> deleteService(HttpServletRequest request,@PathVariable Long id){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(id, request.getHeader("Authorization"))){
            throw new LoginException("Unauthorized");
        }
        System.out.println("delete");
        System.out.println(id);
        port.deleteService(id);
        return ResponseEntity.noContent().build();
    }
    private boolean isLoggedDentist(Long id,String token){
        return port.getService(id).getDentist().getId().equals(loginServicePort.getDentist(token).getId());
    }
}
