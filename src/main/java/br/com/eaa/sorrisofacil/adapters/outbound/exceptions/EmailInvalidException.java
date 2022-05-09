package br.com.eaa.sorrisofacil.adapters.outbound.exceptions;

public class EmailInvalidException extends RuntimeException {
    public EmailInvalidException(String email_already_registered) {
        super(email_already_registered);
    }
}
