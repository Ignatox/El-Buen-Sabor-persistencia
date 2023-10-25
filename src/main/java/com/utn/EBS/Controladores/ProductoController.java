package com.utn.EBS.Controladores;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Enumeraciones.TipoProducto;
import com.utn.EBS.Servicios.ProductoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/productos")

public class ProductoController extends BaseControllerImpl<Producto, ProductoServiceImpl>{
@GetMapping("/buscarPorDenominacion")
public ResponseEntity<?> buscarPorDenominacio(@RequestParam String denominacion){
    try {
        return  ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorDenominacion(denominacion));
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
    }
}
    @GetMapping("/buscarPorTipoProducto")
    public ResponseEntity<?> buscarPorTipoProducto(@RequestParam TipoProducto tipoProducto){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorTipoProducto(tipoProducto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/buscarPorDenominacionPage")
    public ResponseEntity<?> buscarPorDenominacio(@RequestParam String denominacion, Pageable pageable){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorDenominacion(denominacion, pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/buscarPorTipoProductoPage")
    public ResponseEntity<?> buscarPorTipoProducto(@RequestParam TipoProducto tipoProducto, Pageable pageable){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorTipoProducto(tipoProducto, pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }
}
