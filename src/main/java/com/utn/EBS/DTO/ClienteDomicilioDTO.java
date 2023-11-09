package com.utn.EBS.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ClienteDomicilioDTO {
    Long id;
    String calle;
    String numero;
    String localidad;
}
