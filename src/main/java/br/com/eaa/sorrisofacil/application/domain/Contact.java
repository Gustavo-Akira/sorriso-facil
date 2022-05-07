package br.com.eaa.sorrisofacil.application.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    private Long id;
    private Address address;
    private List<Telephone> telephones;
    private Client client;
}
