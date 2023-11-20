package com.utn.EBS.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ProductoPantallaPrincipalDTO {
    Long idProducto;
    int tiempoEstimadoCocina;
    String nombre;
    String descripcion;
    String foto;
    double precio;

    public ProductoPantallaPrincipalDTO(Long idProducto, int tiempoEstimadoCocina, String nombre, String descripcion, String foto, double precio) {
        this.idProducto = idProducto;
        this.tiempoEstimadoCocina = tiempoEstimadoCocina;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precio = precio;
    }
}
