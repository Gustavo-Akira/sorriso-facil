package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.address.AddressDTO;
import br.com.eaa.sorrisofacil.adapters.dto.address.AddressReturn;
import br.com.eaa.sorrisofacil.adapters.dto.address.AddressUpdateDTO;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import br.com.eaa.sorrisofacil.application.domain.Address;
import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.address.AddressServicePort;
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
public class AddressController {

    @Autowired
    private AddressServicePort port;

    @Autowired
    private LoginServicePort loginServicePort;

    @Autowired
    private ClientServicePort clientServicePort;

    @Autowired
    private SecurityUtil util;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/client/{clientId}/addresses/{page}")
    public Page<AddressReturn> getAddresses(HttpServletRequest request, @PathVariable(name = "page") int page, @PathVariable(name = "clientId") Long id){
      util.dentistAuthorized(request);
      if(!isLoggedDentist(request, id)){
          throw new LoginException("Unauthorized");
      }
      return port.getAddresses(new PageInformation(5,page),clientServicePort.getClient(id)).map(x->mapper.map(x,AddressReturn.class));
    }
    @GetMapping("/client/{clientId}/addresses")
    public Page<AddressReturn> getAddresses(HttpServletRequest request,  @PathVariable(name = "clientId") Long id){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, id)){
            throw new LoginException("Unauthorized");
        }
        return port.getAddresses(new PageInformation(5,0),clientServicePort.getClient(id)).map(x->mapper.map(x,AddressReturn.class));
    }

    @PostMapping("/client/{clientId}/address")
    public ResponseEntity<AddressReturn> saveAddress(HttpServletRequest request, @PathVariable Long clientId, @RequestBody AddressDTO dto){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        dto.setContact(clientServicePort.getClient(clientId).getContacts());
        Address address = port.insertAddress(mapper.map(dto,Address.class));
        return ResponseEntity.created(URI.create("/client/"+clientId+"/address"+address.getId())).body(mapper.map(address,AddressReturn.class));
    }

    @PutMapping("/client/{clientId}/address/{id}")
    public ResponseEntity<AddressReturn> updateAddress(HttpServletRequest request, @PathVariable(name = "clientId") Long clientId, @RequestBody AddressUpdateDTO dto, @PathVariable(name = "id") Long id){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        if(!isAddressFromClient(clientId, id)){
            throw new NotFoundElementException("This Address doesn't exists");
        }
        return ResponseEntity.ok(mapper.map(port.updateAddress(id,mapper.map(dto,Address.class)),AddressReturn.class));
    }
    @DeleteMapping("/client/{clientId}/address/{id}")
    public ResponseEntity<Void> deleteAddress(HttpServletRequest request, @PathVariable(name = "clientId") Long clientId, @PathVariable(name = "id") Long id){
        util.dentistAuthorized(request);
        if(!isLoggedDentist(request, clientId)){
            throw new LoginException("Unauthorized");
        }
        if(!isAddressFromClient(clientId, id)){
            throw new NotFoundElementException("This Address doesn't exists");
        }
        port.removeAddress(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isAddressFromClient(Long clientId, Long id) {
        return clientServicePort.getClient(clientId).getContacts().getAddress().getId().equals(id);
    }

    private boolean isLoggedDentist(HttpServletRequest request, Long id){
        Client client = clientServicePort.getClient(id);
        return client.getDentist().equals(loginServicePort.getDentist(request.getHeader("Authorization")));
    }
}
