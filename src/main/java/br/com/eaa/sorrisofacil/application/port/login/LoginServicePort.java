package br.com.eaa.sorrisofacil.application.port.login;

import br.com.eaa.sorrisofacil.adapters.dto.login.LoginResponseDTO;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface LoginServicePort {
    boolean validToken(String token);
    LoginResponseDTO login(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
    User getUser(String token);
    Dentist getDentist(String token);
    Class getRole(String token);
}
