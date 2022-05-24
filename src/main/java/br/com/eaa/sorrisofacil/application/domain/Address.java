package br.com.eaa.sorrisofacil.application.domain;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "contact")
@EqualsAndHashCode(exclude = "contact")
public class Address {
    private Long id;
    private String street;
    private int number;
    private String neighborhood;
    private String city;
    private String state;
    private String country;
    private String cep;
    private Contact contact;
}
