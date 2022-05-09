package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.exception.ExceptionResponseDTO;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.EmailInvalidException;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.NotFoundElementException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<ExceptionResponseDTO> loginExceptionHandler(LoginException e){
        ExceptionResponseDTO dto = new ExceptionResponseDTO(LocalDateTime.now(),401,e.getMessage(),e.getLocalizedMessage());
        return ResponseEntity.status(401).body(dto);
    }

    @ExceptionHandler(NotFoundElementException.class)
    public ResponseEntity<ExceptionResponseDTO> notFoundExceptionHandler(NotFoundElementException e){
        ExceptionResponseDTO dto = new ExceptionResponseDTO(LocalDateTime.now(),404,e.getMessage(),e.getLocalizedMessage());
        return ResponseEntity.status(404).body(dto);
    }
    @ExceptionHandler(EmailInvalidException.class)
    public ResponseEntity<ExceptionResponseDTO> emailInvalidExceptionHandler(EmailInvalidException e){
        ExceptionResponseDTO dto = new ExceptionResponseDTO(LocalDateTime.now(),404,e.getMessage(),"validation");
        return ResponseEntity.status(400).body(dto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ExceptionResponseDTO dto = new ExceptionResponseDTO(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(), String.join(" ", details), "validation");
        return new ResponseEntity(dto, HttpStatus.BAD_REQUEST);
    }
}
