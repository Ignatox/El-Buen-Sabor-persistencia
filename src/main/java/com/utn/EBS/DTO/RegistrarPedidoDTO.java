package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.TipoEnvio;
import lombok.Data;

import java.util.List;

@Data
public class RegistrarPedidoDTO {
    private List<DetallePedidoDTO> detallesPedido;
    private Long idCliente;
    private TipoEnvio tipoEnvio;
}
