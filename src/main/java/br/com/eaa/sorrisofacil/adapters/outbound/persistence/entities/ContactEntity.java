package br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities;

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
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "contact")
    private AddressEntity address;
    @OneToMany(mappedBy = "contact")
    private List<TelephoneEntity> telephones;
}
