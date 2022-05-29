package br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities;

import br.com.eaa.sorrisofacil.application.domain.Client;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "contact",orphanRemoval = true)
    private AddressEntity address;
    @OneToMany(mappedBy = "contact",orphanRemoval = true)
    private List<TelephoneEntity> telephones;
    @OneToOne( orphanRemoval = true)
    private ClientEntity client;
}
