package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.DetallePedido;
import com.utn.EBS.Enumeraciones.TipoEnvio;
import lombok.Data;

import java.util.List;

@Data
public class RegistrarPedidoDTO {
    private List<DetallePedidoDto> detallesPedido;
    private Long idCliente;
    private TipoEnvio tipoEnvio;
}
