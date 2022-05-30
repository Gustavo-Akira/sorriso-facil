package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.telephone.TelephoneDTO;
import br.com.eaa.sorrisofacil.adapters.dto.telephone.TelephoneReturn;
import br.com.eaa.sorrisofacil.adapters.dto.telephone.TelephoneUpdateDTO;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Telephone;
import br.com.eaa.sorrisofacil.application.port.client.ClientServicePort;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import br.com.eaa.sorrisofacil.application.port.telephone.TelephoneServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
public class TelephoneController {

    @Autowired
    private LoginServicePort loginServicePort;

    @Autowired
    private SecurityUtil util;

    @Autowired
    private TelephoneServicePort port;

    @Autowired
    private ClientServicePort clientServicePort;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/client/{clientId}/telephones")
    public Page<TelephoneReturn> getTelephones(HttpServletRequest request, @PathVariable Long clientId){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        return port.getTelephones(new PageInformation(5,0), clientServicePort.getClient(clientId)).map(x->mapper.map(x,TelephoneReturn.class));
    }
    @GetMapping("/client/{clientId}/telephones/{page}")
    public Page<TelephoneReturn> getTelephones(HttpServletRequest request, @PathVariable(name = "clientId") Long clientId, @PathVariable(name = "page") int page){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        return port.getTelephones(new PageInformation(5,page), clientServicePort.getClient(clientId)).map(x->mapper.map(x,TelephoneReturn.class));
    }

    @GetMapping("/client/{clientId}/telephone/{telephoneId}")
    public TelephoneReturn getTelephone(HttpServletRequest request, @PathVariable(name = "clientId") Long clientId, @PathVariable(name = "telephoneId") Long telephoneId){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        if(!isTelephoneFromClient(clientId, telephoneId)){
            throw new LoginException("Unauthorized");
        }
        return mapper.map(port.getTelephone(telephoneId),TelephoneReturn.class);
    }
    @PostMapping("client/{clientId}/telephone")
    public ResponseEntity<TelephoneReturn> saveTelephone(HttpServletRequest request, @PathVariable Long clientId, @RequestBody TelephoneDTO dto){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        dto.setContact(clientServicePort.getClient(clientId).getContacts());
        Telephone telephone = port.insertTelephone(mapper.map(dto, Telephone.class));
        return ResponseEntity.created(URI.create("client/"+clientId+"/telephone/"+telephone.getId())).body(mapper.map(telephone,TelephoneReturn.class));
    }

    @PutMapping("client/{clientId}/telephone/{telephoneId}")
    public ResponseEntity<TelephoneReturn> updateTelephone(HttpServletRequest request, @RequestBody TelephoneUpdateDTO dto, @PathVariable(name = "telephoneId") Long telephoneId, @PathVariable(name = "clientId") Long clientId){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        if(!isTelephoneFromClient(clientId, telephoneId)){
            throw new LoginException("Unauthorized");
        }
        return ResponseEntity.ok(mapper.map(port.updateTelephone(telephoneId,mapper.map(dto, Telephone.class)),TelephoneReturn.class));
    }

    @DeleteMapping("client/{clientId}/telephone/{telephoneId}")
    public ResponseEntity<Void> removeTelephone(HttpServletRequest request, @PathVariable(name = "clientId") Long clientId, @PathVariable(name = "telephoneId") Long telephoneId){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        if(!isTelephoneFromClient(clientId, telephoneId)){
            throw new LoginException("Unauthorized");
        }
        port.removeTelephone(telephoneId);
        return ResponseEntity.noContent().build();
    }
    private boolean isTelephoneFromClient(Long clientId, Long id) {
        System.out.println(clientServicePort.getClient(clientId).getContacts().getTelephones());
        return clientServicePort.getClient(clientId).getContacts().getTelephones().stream().anyMatch(x->x.getId().equals(id));
    }

    private boolean isLoggedDentist(HttpServletRequest request, Long id){
        Client client = clientServicePort.getClient(id);
        return client.getDentist().equals(loginServicePort.getDentist(request.getHeader("Authorization")));
    }
}
