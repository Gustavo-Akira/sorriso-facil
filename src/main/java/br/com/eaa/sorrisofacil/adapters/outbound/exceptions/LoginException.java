package br.com.eaa.sorrisofacil.adapters.outbound.exceptions;

public class LoginException extends  RuntimeException{
    public LoginException(String exception){
        super(exception);
    }
}
