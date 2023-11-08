package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.ProductoIngrediente;
import lombok.Data;

import java.util.List;

@Data
public class ProductoDTO {

    Long idProducto;
    int cantidad;
    int tiempoEstimadoCocina;
    String nombre;
    String descripcion;
    String foto;
    List<ProductoIngrediente> ingredientes;
    String denominacion;
    String receta;

}

