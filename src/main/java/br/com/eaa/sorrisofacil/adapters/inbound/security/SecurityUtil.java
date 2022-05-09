package br.com.eaa.sorrisofacil.adapters.inbound.security;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.port.LoginServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
public class SecurityUtil {
    @Autowired
    private LoginServicePort loginServicePort;

    public void adminAuthorized(HttpServletRequest request){
        if(loginServicePort.getRole(request.getHeader("Authorization")).equals(Dentist.class)){
            throw new LoginException("Unauthorized Request");
        }
    }

    public boolean dentistAuthorized(HttpServletRequest request, boolean admin){
        if(loginServicePort.getRole(request.getHeader("Authorization")).equals(Administrator.class)){
            if(!admin) {
                throw new LoginException("Unauthorized Request");
            }else{
                return false;
            }
        }
        return true;
    }
}
