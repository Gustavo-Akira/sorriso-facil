package br.com.eaa.sorrisofacil.adapters.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDTO {
    private LocalDateTime timeStamp;
    private int status;
    private String error;
    private String path;
}
