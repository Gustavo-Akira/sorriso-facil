package br.com.eaa.sorrisofacil.adapters.inbound.security;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityUtil {
    @Autowired
    private LoginServicePort loginServicePort;

    public void adminAuthorized(HttpServletRequest request){
        if(loginServicePort.getRole(request.getHeader("Authorization")).equals(Dentist.class)){
            throw new LoginException("Unauthorized Request");
        }
    }

    public boolean dentistAuthorized(HttpServletRequest request){
        if(loginServicePort.getRole(request.getHeader("Authorization")).equals(Administrator.class)){
            throw new LoginException("Unauthorized Request");
        }
        return true;
    }
}
