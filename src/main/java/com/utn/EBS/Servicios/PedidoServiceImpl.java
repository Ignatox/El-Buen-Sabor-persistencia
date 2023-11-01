package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.DetallePedidoDto;
import com.utn.EBS.DTO.RegistrarPedidoDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.DetallePedido;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Enumeraciones.EstadoPedido;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.ClienteRepository;
import com.utn.EBS.Repositorios.PedidoRepository;
import com.utn.EBS.Repositorios.ProductoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PedidoServiceImpl extends BaseServiceImpl<Pedido, Long> implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }


    @Override
    @Transactional
    public Pedido registrarPedido(RegistrarPedidoDTO pedidoDTO) throws Exception {
        try {
            // buscamos al cliente del pedido
            Optional<Cliente> cliente = clienteRepository.findById(pedidoDTO.getIdCliente());
            if (cliente.isEmpty()) throw new Exception("no se encontro el cliente");

            List<DetallePedido> detallesPedido = new ArrayList<DetallePedido>();
            // creamos la lista con todos los DetallePedido
            // y calculamos el total tambien
            double totalPedido = 0;

            for (DetallePedidoDto detalleDTO : pedidoDTO.getDetallesPedido()) {
                Optional<Producto> productoSolicitado = productoRepository.findById(detalleDTO.getIdProducto());
                if (productoSolicitado.isEmpty()) throw new Exception("Producto no encontrado");

                DetallePedido detallePedido = DetallePedido.builder()
                        .producto(productoSolicitado.get())
                        .cantidad(detalleDTO.getCantidad())
                        .subtotal(productoSolicitado.get().getPrecioCompra() * detalleDTO.getCantidad())
                        .build();
                detallesPedido.add(detallePedido);
                totalPedido += detallePedido.getSubtotal();
            }

            Pedido pedido = Pedido.builder()
                    .estado(EstadoPedido.INICIADO)
                    .fecha(new Date().toString())
                    .cliente(cliente.get())
                    .tipoEnvio(pedidoDTO.getTipoEnvio())
                    .horaEstimadaEntrega("08:00")
                    .detallePedidos(detallesPedido)
                    .total(totalPedido)
                    .build();
            // guardamos el pedido
            return pedidoRepository.save(pedido);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public Page<Pedido> buscarPedidosAPrerarar(Pageable pageable) throws Exception{
        try{
            Page<Pedido> pedidosEncontrados = pedidoRepository.buscarPedidosAPreparar(pageable);
            if(pedidosEncontrados == null){
                throw new Exception("No hay pedidos para preparar");
            }else{
                return pedidosEncontrados;
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Boolean cambiarEstadoPedido(Long id) throws Exception{
        try{
            Pedido pedido=pedidoRepository.buscarPorId(id);

            if(pedido == null) {
                throw new Exception("el pedido que intenta editar no existe");
            }

            pedido.setEstado(EstadoPedido.INICIADO);

            return true;

        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }


}
