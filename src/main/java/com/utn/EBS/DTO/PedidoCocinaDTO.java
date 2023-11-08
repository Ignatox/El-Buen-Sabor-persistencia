package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoPedido;
import lombok.Data;

import java.util.List;

@Data
public class PedidoCocinaDTO {
    Long idPedido;
    String fecha;
    EstadoPedido estado;
    List<ProductoDTO> productos;

}
