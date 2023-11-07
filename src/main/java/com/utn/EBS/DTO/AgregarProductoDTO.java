package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoProducto;
import lombok.Data;

import java.util.List;

@Data
public class AgregarProductoDTO {
    int tiempoEstimadoCocina;
    String nombre;
    String descripcion;
    double precio;
    String receta;
    Long idRubro;
    String foto;
    List<ProductoIngredienteDTO> ingredienteDTOS;
    EstadoProducto estado;     //Se lo agregué pq lo requiere el formulario

}
