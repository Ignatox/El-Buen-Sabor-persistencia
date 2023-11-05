package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.ProductoIngrediente;

import java.util.List;

public class ProductoDTO {
    int cantidad;
    int tiempoEstimadoCocina;
    String nombre;
    String descripcion;
    String foto;
    List<ProductoIngrediente> ingredientes;
}

