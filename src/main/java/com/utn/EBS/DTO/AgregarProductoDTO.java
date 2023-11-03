package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.TipoProducto;
import lombok.Data;

import java.util.List;

@Data
public class AgregarProductoDTO {
    int tiempoEstimadoCocina;
    String denominacion;
    double precioVenta;
    double precioCompra;
    String receta;
    TipoProducto tipoProducto;
    Long idRubro;
    List<ProductoIngredienteDTO> ingredienteDTOS;
    boolean estado;     //Se lo agregué pq lo requiere el formulario

}
