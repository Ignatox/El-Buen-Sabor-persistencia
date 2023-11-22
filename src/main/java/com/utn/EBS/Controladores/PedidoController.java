package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.PedidoCocinaDTO;
import com.utn.EBS.DTO.BuscarPedidoEntreFechaDTO;
import com.utn.EBS.DTO.RegistrarPedidoDTO;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Servicios.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoServiceImpl>{

    @Autowired
    PedidoServiceImpl pedidoService;

    @PostMapping("/crearPedido")
    public ResponseEntity<?> registrarPedido(@RequestBody RegistrarPedidoDTO pedidoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.registrarPedido(pedidoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/buscarPedidosAPreparar")
    public ResponseEntity<?> buscarPedidosAPreparar(@RequestBody Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedidosAPrerarar(pageable));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/editarEstado")
    public ResponseEntity<?> editarEstadoPedido(@RequestBody PedidoCocinaDTO pedidoCocinaDTO){
        try{
           return ResponseEntity.status(HttpStatus.OK).body(pedidoService.cambiarEstadoPedido(pedidoCocinaDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/buscarPorFecha")
    public ResponseEntity<?> buscarPorFecha(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPorFecha(pageable));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pedidoEntreFechas")
    public ResponseEntity<?> buscarPedidosEntreFecha(@RequestBody BuscarPedidoEntreFechaDTO buscarPedidoEntreFechaDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedidosEntreFecha(buscarPedidoEntreFechaDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/movimientoMonetario")
    public ResponseEntity<?> buscarMovimientosMonetarios(@RequestBody BuscarPedidoEntreFechaDTO buscarPedidoEntreFechaDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarMovimientosMonetarios(buscarPedidoEntreFechaDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}/historialPedidos")
    public ResponseEntity<?> buscarPorCliente(@RequestParam("id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPorCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

  
    @GetMapping("/EntregarPedidos")
    public ResponseEntity<?> buscarPedidosAEntregar(Pageable pageable ){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPorCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/EntregarPedidos") //delivery
    public ResponseEntity<?> buscarPedidosAEntregar(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedidosAEntregar(pageable));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/PedidosCaja")
    public ResponseEntity<?> buscarPedidosAConfirmar( Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPedidosAConfirmar(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/PedidosCaja/{$id}")
   public ResponseEntity<?> cambiarEstadoCaja(@RequestBody Long id){
    try{
        return  ResponseEntity.status(HttpStatus.OK).body(servicio.cambiarEstadoCaja(id));
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    }

}
