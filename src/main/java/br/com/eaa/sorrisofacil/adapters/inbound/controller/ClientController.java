package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.client.ClientDTO;
import br.com.eaa.sorrisofacil.adapters.dto.client.ClientReturn;
import br.com.eaa.sorrisofacil.adapters.dto.client.ClientUpdateDTO;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.client.ClientServicePort;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
public class ClientController {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ClientServicePort port;

    @Autowired
    private SecurityUtil util;

    @Autowired
    private LoginServicePort loginServicePort;

    @GetMapping("clients")
    public Page<ClientReturn> getClients(HttpServletRequest request){
        util.dentistAuthorized(request);
        return port.getClients(new PageInformation(5,0), loginServicePort.getDentist(request.getHeader("Authorization"))).map(x->mapper.map(x,ClientReturn.class));
    }

    @GetMapping("clients/{page}")
    public Page<ClientReturn> getClients(HttpServletRequest request, @PathVariable int page) {
        util.dentistAuthorized(request);
        return port.getClients(new PageInformation(5, page), loginServicePort.getDentist(request.getHeader("Authorization"))).map(x -> mapper.map(x, ClientReturn.class));
    }

    @GetMapping("client/{id}")
    public ClientReturn getClient(HttpServletRequest request, @PathVariable Long id) {
        util.dentistAuthorized(request);
        if(!isLoggedDentist(id,request.getHeader("Authorization"))){
            throw  new LoginException("Unauthorized");
        }
        return mapper.map(port.getClient(id),ClientReturn.class);
    }

    @PostMapping("client")
    public ResponseEntity<ClientReturn> saveClient(HttpServletRequest request, @RequestBody ClientDTO dto){
        util.dentistAuthorized(request);
        dto.setDentist(loginServicePort.getDentist(request.getHeader("Authorization")));
        Client client = port.insertClient(mapper.map(dto,Client.class));
        return ResponseEntity.created(URI.create("client/"+client.getId())).body(mapper.map(client,ClientReturn.class));
    }

    @PutMapping("client/{id}")
    public ResponseEntity<Void> updateClient(HttpServletRequest request, @RequestBody ClientUpdateDTO dto, @PathVariable Long id){
        util.dentistAuthorized(request);
        if(isLoggedDentist(id,request.getHeader("Authorization"))){
            throw  new LoginException("Unauthorized");
        }
        port.updateClient(id,mapper.map(dto, Client.class));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<Void> deleteClient(HttpServletRequest request, @PathVariable Long id){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(id,request.getHeader("Authorization"))){
            throw  new LoginException("Unauthorized");
        }
        port.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
    private boolean isLoggedDentist(Long id,String token){
        return port.getClient(id).getDentist().getId().equals(loginServicePort.getDentist(token).getId());
    }
}

