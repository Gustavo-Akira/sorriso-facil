package br.com.eaa.sorrisofacil.application.domain;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "contact")
@EqualsAndHashCode(exclude = "contact")
public class Telephone {
    private Long id;
    private String ddd;
    private String number;
    private Contact contact;
}
