package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.RegistrarPedidoDTO;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Servicios.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
