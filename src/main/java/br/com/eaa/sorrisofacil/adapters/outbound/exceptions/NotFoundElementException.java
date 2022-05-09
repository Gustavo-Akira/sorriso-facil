package br.com.eaa.sorrisofacil.adapters.outbound.exceptions;

public class NotFoundElementException extends RuntimeException {
    public NotFoundElementException(String error){
        super(error);
    }
}
