package com.utn.EBS.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoPantallaPrincipalDTO {
    private String nombre;
    private String descripcion;
    private int tiempoEstimadoCocina;
    private double precio;
    private String foto;
    private String rubro;
}
