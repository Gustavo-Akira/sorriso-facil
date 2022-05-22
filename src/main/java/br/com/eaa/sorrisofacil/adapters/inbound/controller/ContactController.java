package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.contact.ContactDTO;
import br.com.eaa.sorrisofacil.adapters.dto.contact.ContactReturn;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.Contact;
import br.com.eaa.sorrisofacil.application.port.client.ClientServicePort;
import br.com.eaa.sorrisofacil.application.port.contact.ContactServicePort;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;

@RestController
public class ContactController {

    @Autowired
    private ContactServicePort port;

    @Autowired
    private SecurityUtil util;

    @Autowired
    private LoginServicePort loginServicePort;

    @Autowired
    private ClientServicePort clientServicePort;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("contact")
    public ResponseEntity<ContactReturn> saveContact(HttpServletRequest request, @RequestBody @Valid ContactDTO dto){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(dto.getId(),request.getHeader("Authorization"))){
            throw new LoginException("Unauthorized");
        }
        Client client = clientServicePort.getClient(dto.getId());
        Contact contact = new Contact();
        contact.setClient(client);
        contact = port.insertContact(contact);
        client.setContacts(contact);
        client = clientServicePort.updateClient(dto.getId(),client);
        System.out.println(client);
        return ResponseEntity.created(URI.create("")).body(mapper.map(contact,ContactReturn.class));
    }

    private boolean isLoggedDentist(Long id,String token){
        return clientServicePort.getClient(id).getDentist().getId().equals(loginServicePort.getDentist(token).getId());
    }
}
