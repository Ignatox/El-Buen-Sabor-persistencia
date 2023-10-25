package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.TipoProducto;
import lombok.Data;

@Data
public class AgregarProductoDTO {
    int tiempoEstimadoCocina;
    String denominacion;
    double precioVenta;
    double precioCompra;
    int stockActual;
    int stockMinimo;
    String unidadMedida;
    String receta;
    TipoProducto tipoProducto;
    Long idRubro;
}
