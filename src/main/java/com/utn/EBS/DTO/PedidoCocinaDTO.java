package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoPedido;
import lombok.Data;

@Data
public class PedidoCocinaDTO {
    Long idProducto;
    String fecha;
    EstadoPedido estado;

}
