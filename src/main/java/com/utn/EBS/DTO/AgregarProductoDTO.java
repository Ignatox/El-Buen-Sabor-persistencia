package com.utn.EBS.DTO;

import lombok.Data;

import java.util.List;

@Data
public class AgregarProductoDTO {
    int tiempoEstimadoCocina;
    String nombre;
    double precioVenta;
    double precioCompra;
    String receta;
    Long idRubro;
    List<ProductoIngredienteDTO> ingredienteDTOS;
    boolean estado;     //Se lo agregué pq lo requiere el formulario

}
