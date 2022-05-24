package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Address;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.address.AddressServicePort;
import br.com.eaa.sorrisofacil.application.port.client.ClientServicePort;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/client/{clientId}/addresses/{page}")
    public Page<Address> getAddresses(HttpServletRequest request, @PathVariable(name = "page") int page, @PathVariable(name = "clientId") Long id){
      util.dentistAuthorized(request);
      if(!isLoggedDentist(request, id)){
          throw new LoginException("Unauthorized");
      }
      return port.getAddresses(new PageInformation(5,page),clientServicePort.getClient(id));
    }

    private boolean isLoggedDentist(HttpServletRequest request, Long id){
        return clientServicePort.getClient(id).getDentist().equals(loginServicePort.getDentist(request.getHeader("Authorization")));
    }
}
