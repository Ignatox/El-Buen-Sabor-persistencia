package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.RegistrarPedidoDTO;
import com.utn.EBS.Entidades.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService extends BaseService<Pedido, Long>{


    public Pedido registrarPedido(RegistrarPedidoDTO pedidoDTO) throws Exception;

    public Page<Pedido> buscarPedidosAPrerarar(Pageable pageable) throws Exception;

   public Boolean cambiarEstadoPedido(Long id) throws Exception;
   public Page<Pedido> buscarPorFecha(Pageable pageable) throws Exception;

}
