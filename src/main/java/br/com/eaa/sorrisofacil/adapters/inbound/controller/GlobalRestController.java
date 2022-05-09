package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalRestController {
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> loginExceptionHandler(LoginException e){
        return ResponseEntity.status(401).header("Content-Type","application/json").body("{\"error\": \""+e.getMessage()+"\"}");
    }
}
