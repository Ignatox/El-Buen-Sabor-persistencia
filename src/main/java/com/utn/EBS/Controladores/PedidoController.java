package com.utn.EBS.Controladores;

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
    public ResponseEntity<?> buscarPedidosAPreparar(Pageable pageable){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedidosAPrerarar(pageable));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/editarEstado")
    public ResponseEntity<?> editarEstadoPedido(Long id){
        try{
           return ResponseEntity.status(HttpStatus.OK).body(pedidoService.cambiarEstadoPedido(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/pedidoEntreFechas")
    public ResponseEntity<?> buscarPedidosEntreFecha(BuscarPedidoEntreFechaDTO buscarPedidoEntreFechaDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPedidosEntreFecha(buscarPedidoEntreFechaDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/movimientoMonetario")
    public ResponseEntity<?> buscarMovimientosMonetarios(BuscarPedidoEntreFechaDTO buscarPedidoEntreFechaDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarMovimientosMonetarios(buscarPedidoEntreFechaDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/historialPedidos")
    public ResponseEntity<?> buscarPorCliente(Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pedidoService.buscarPorCliente(id));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
