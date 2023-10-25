package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.RegistrarPedidoDTO;
import com.utn.EBS.Entidades.Pedido;

public interface PedidoService extends BaseService<Pedido, Long>{

    public Pedido registrarPedido(RegistrarPedidoDTO pedidoDTO) throws Exception;
}
