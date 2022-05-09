package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.login.LoginRequestDTO;
import br.com.eaa.sorrisofacil.adapters.dto.login.LoginResponseDTO;
import br.com.eaa.sorrisofacil.application.port.login.LoginServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
public class LoginController {

    @Autowired
    private LoginServicePort port;

    @PostMapping("login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return port.login(dto.getEmail(),dto.getPassword());
    }
}
