package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.EstadoPedido;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PedidoCocinaDTO {
    Long idPedido;
    Date fecha;
    EstadoPedido estado;
    String productos;

}
