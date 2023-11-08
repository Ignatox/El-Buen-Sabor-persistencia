package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.BuscarPedidoEntreFechaDTO;
import com.utn.EBS.DTO.MovimientoMonetarioDTO;
import com.utn.EBS.DTO.RegistrarPedidoDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoService extends BaseService<Pedido, Long>{


    public Pedido registrarPedido(RegistrarPedidoDTO pedidoDTO) throws Exception;

    public Page<Pedido> buscarPedidosAPrerarar(Pageable pageable) throws Exception;

   public Boolean cambiarEstadoPedido(Long id) throws Exception;

   public List<Pedido> buscarPedidosEntreFecha(BuscarPedidoEntreFechaDTO buscarPedidoEntreFechaDTO) throws Exception;

   public List<MovimientoMonetarioDTO> buscarMovimientosMonetarios(BuscarPedidoEntreFechaDTO buscarPedidoEntreFechaDTO) throws Exception;

   public List<Pedido> buscarPorCliente(Long id) throws Exception;
}
