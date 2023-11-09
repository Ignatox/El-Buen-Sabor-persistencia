package com.utn.EBS.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RubroPantallaPrincipalDTO {
    List<ProductoPantallaPrincipalDTO> productos;
    private String nombreRubro;
}
