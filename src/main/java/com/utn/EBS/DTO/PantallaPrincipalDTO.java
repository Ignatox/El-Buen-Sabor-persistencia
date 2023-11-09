package com.utn.EBS.DTO;

import lombok.Data;

import java.util.List;

@Data
public class PantallaPrincipalDTO {
    String nombreCategoria;
    List<ProductoPantallaPrincipalDTO> productos;
}
