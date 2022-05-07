package br.com.eaa.sorrisofacil.adapters.outbound.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.UUID;

@Data
@AllArgsConstructor
@Entity
public class AdministratorEntity extends UserEntity{
}
