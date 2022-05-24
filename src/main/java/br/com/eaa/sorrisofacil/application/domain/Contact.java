package br.com.eaa.sorrisofacil.application.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"client"})
@EqualsAndHashCode(exclude = "client")
public class Contact {
    private Long id;
    private Address address;
    private List<Telephone> telephones = new ArrayList<>();
    private Client client;
}
