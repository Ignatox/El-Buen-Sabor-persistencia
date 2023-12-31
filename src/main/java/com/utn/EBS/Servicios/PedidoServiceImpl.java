package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.*;
import com.utn.EBS.DTO.DetallePedidoDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.DetallePedido;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Entidades.*;
import com.utn.EBS.Enumeraciones.EstadoPedido;


import com.utn.EBS.DTO.ProductoDTO;
import com.utn.EBS.DTO.RegistrarPedidoDTO;
import com.utn.EBS.DTO.PedidoCocinaDTO;


import com.utn.EBS.Enumeraciones.FormaPago;
import com.utn.EBS.Repositorios.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;
    @Autowired
    private FacturaRepository facturaRepository;

    public PedidoServiceImpl(BaseRepository<Pedido, Long> baseRepository) {
        super(baseRepository);
    }


    @Override
    @Transactional
    public Pedido registrarPedido(RegistrarPedidoDTO pedidoDTO) throws Exception {
        try {
            // buscamos al cliente del pedido
            UserDetails usuario = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            System.out.println(usuario.getUsername());

            Cliente cliente = clienteRepository.findByUsername(usuario.getUsername());
            //if (cliente.isEmpty()) throw new Exception("no se encontro el cliente");
            System.out.println(cliente.getNombre());
            List<DetallePedido> detallesPedido = new ArrayList<DetallePedido>();
            // creamos la lista con todos los DetallePedido
            // y calculamos el total tambien
            double totalPedido = 0;

            for (DetallePedidoDTO detalleDTO : pedidoDTO.getDetallesPedido()) {
                Optional<Producto> productoSolicitado = productoRepository.findById(detalleDTO.getIdProducto());
                if (productoSolicitado.isEmpty()) throw new Exception("Producto no encontrado");

                DetallePedido detallePedido = DetallePedido.builder()
                        .producto(productoSolicitado.get())
                        .cantidad(detalleDTO.getCantidad())
                        .subtotal(productoSolicitado.get().getPrecio() * detalleDTO.getCantidad())
                        .build();
                detallesPedido.add(detallePedido);
                totalPedido += detallePedido.getSubtotal();
            }

            Pedido pedido = Pedido.builder()
                    .estado(EstadoPedido.INICIADO)
                    .fecha(new Date())
                    .tipoPago(pedidoDTO.getTipoPago())
                    .cliente(cliente)
                    .tipoEnvio(pedidoDTO.getTipoEnvio())
                    .horaEstimadaEntrega("08:00")
                    .detallePedido(detallesPedido)
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
    public Page<PedidoCocinaDTO> buscarPedidosAPrerarar(Pageable pageable) throws Exception {
        try {
            Page<Pedido> pedidosEncontrados = pedidoRepository.buscarPedidosAPreparar(pageable);
            if (pedidosEncontrados == null) {
                throw new Exception("No hay pedidos para preparar");
            } else {
                List<PedidoCocinaDTO> pedidosCocina = null;

                for (Pedido pedido : pedidosEncontrados) {
                    List<DetallePedido> detallesPedido = pedido.getDetallePedido();
                    PedidoCocinaDTO pedidoCocina = new PedidoCocinaDTO();
                    String productos = new String();

                  for (DetallePedido detalles : detallesPedido) {
                       // ProductoDTO productoCocina = new ProductoDTO();
/*
                        productoCocina.setIdProducto(detalles.getProducto().getId());
                        productoCocina.setFoto(detalles.getProducto().getFoto());
                        productoCocina.setCantidad(detalles.getCantidad());
                        productoCocina.setNombre(detalles.getProducto().getNombre());
                        productoCocina.setDescripcion(detalles.getProducto().getDescripcion());
                        productoCocina.setIngredientes(detalles.getProducto().getIngredientes());
                        productoCocina.setReceta(detalles.getProducto().getReceta());
                        productoCocina.setTiempoEstimadoCocina(detalles.getProducto().getTiempoEstimadoCocina());*/

                        productos = productos + detalles.getProducto().getNombre();


                    }

                    pedidoCocina.setIdPedido(pedido.getId());
                    pedidoCocina.setFecha(pedido.getFecha());
                    pedidoCocina.setEstado(EstadoPedido.A_PREPARAR);
                    pedidoCocina.setProductos(productos);

                    pedidosCocina.add(pedidoCocina);
                }

                Page<PedidoCocinaDTO> pedidosAPreparar = new PageImpl<>(pedidosCocina, pageable, pedidosCocina.size());

                return pedidosAPreparar;
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Pedido> buscarPedidosEntreFecha (BuscarPedidoEntreFechaDTO buscarPedidoEntreFechaDTO) throws
            Exception {
        try {
            List<Pedido> pedidosEntreFecha = pedidoRepository.buscarPedidosEntreFecha(buscarPedidoEntreFechaDTO.getFechaInicio(), buscarPedidoEntreFechaDTO.getFechaFin());
            return pedidosEntreFecha;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Boolean cambiarEstadoPedido(PedidoCocinaDTO pedidoCocina) throws Exception {
        try {
            Pedido pedido = pedidoRepository.buscarPorId(pedidoCocina.getIdPedido());
            if (pedido == null) {
                throw new Exception("el pedido que intenta editar no existe");
            } else {
                pedidoCocina.setEstado(EstadoPedido.INICIADO);
                pedido.setEstado(EstadoPedido.INICIADO);

                pedidoRepository.save(pedido);
            }

            return true;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<MovimientoMonetarioDTO> buscarMovimientosMonetarios (BuscarPedidoEntreFechaDTO
                                                                             buscarPedidoEntreFechaDTO) throws Exception {
        List<MovimientoMonetarioDTO> movimientosMonetarios = new ArrayList<>();
    
        try {
            List<Pedido> pedidos = pedidoRepository.findAll();

            for (Pedido pedido : pedidos) {
                MovimientoMonetarioDTO movimientoMonetario = new MovimientoMonetarioDTO();
                movimientoMonetario.setNumeroPedido(pedido.getId().intValue());
                movimientoMonetario.setTotal(pedido.getTotal());

                // Calcula el Costo Total con la función calcularCostoIngredientesPorPedido
                double costo = calcularCostoIngredientesPorPedido(pedido);
                movimientoMonetario.setCosto(costo);

                movimientosMonetarios.add(movimientoMonetario);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return movimientosMonetarios;
    }

    public double calcularCostoIngredientesPorPedido (Pedido pedido){
        double costoTotal = 0.0;

        List<DetallePedido> detallesPedido = pedido.getDetallePedido();
        for (DetallePedido detallePedido : detallesPedido) {
            Producto producto = detallePedido.getProducto();

            for (ProductoIngrediente productoIngrediente : producto.getIngredientes()) {
                Ingrediente ingrediente = productoIngrediente.getIngrediente();
                int cantidadIngrediente = productoIngrediente.getCantidad();
                double costoIngrediente = ingrediente.getCosto();

                costoTotal += cantidadIngrediente * costoIngrediente;
            }
        }

        return costoTotal;
    }

    @Override
    public List<Pedido> buscarPorCliente (Long clienteId) throws Exception {
        try {
            Cliente cliente = clienteRepository.findById(clienteId).orElse(null);
            if (cliente == null) {
                return new ArrayList<>(); // throw new Exception("Cliente no encontrado.");
            }
            List<Pedido> pedidosDelCliente = pedidoRepository.findByCliente(cliente);

            return pedidosDelCliente;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Pedido> buscarPorFecha (Pageable pageable) throws Exception
    {          //Si pinta corregir el nombre del metodo
        try {
            Page<Pedido> pedidosEncontrados = pedidoRepository.buscarPorFecha(pageable);
            if (pedidosEncontrados == null) {
                throw new Exception("No se encuentran pedidos");
            } else {
                return pedidosEncontrados;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<Pedido> buscarPorFecha () throws Exception
    {          //Si pinta corregir el nombre del metodo
        try {
            List<Pedido> pedidosEncontrados = pedidoRepository.buscarPorFecha();
            if (pedidosEncontrados == null) {
                throw new Exception("No se encuentran pedidos");
            } else {
                return pedidosEncontrados;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Pedido> buscarPedidosAEntregar(Pageable pageable) throws Exception{
        try{
            Page<Pedido> pedidosAEntregar = pedidoRepository.buscarPedidosAEntregar(pageable);
            if(pedidosAEntregar == null){
                throw new Exception("Todavía no  hay pedidos para entregar");
            }else{
                return pedidosAEntregar;
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Page<Pedido> buscarPedidosAConfirmar(Pageable pageable) throws Exception{
        try{
            Page<Pedido> pedidosAconfirmar = pedidoRepository.buscarPedidosAconfirmar(pageable);
            if(pedidosAconfirmar == null){
                throw new Exception("No hay pedidos recientes");
            }else{
                return pedidosAconfirmar;
            }
        }catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Pedido cambiarEstadoCaja(Long id) throws Exception{
        try{
            Pedido ped=pedidoRepository.buscarPorId(id);
            Factura fac= facturaRepository.buscarPorId(id);
        if (ped==null || ped.isDeleted()){
            throw new Exception("El pedido no existe o fue eliminado");
        }

            switch (ped.getEstado()) {
                case A_CONFIRMAR:
                    ped.setEstado(EstadoPedido.INICIADO);
                    break;
                case INICIADO:
                    if (fac.getFormaPago() == FormaPago.EFECTIVO) {
                        ped.setEstado(EstadoPedido.ENTREGADO);
                    } else {
                        ped.setEstado(EstadoPedido.EN_CAMINO);
                    }
                    break;
                case EN_CAMINO:
                    ped.setEstado(EstadoPedido.ENTREGADO);
                    break;
                case ENTREGADO:
                    // No se hace nada, es el estado final del pedido
                    break;
            }

        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }


}
