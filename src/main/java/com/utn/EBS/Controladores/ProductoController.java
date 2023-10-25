package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Enumeraciones.TipoProducto;
import com.utn.EBS.Servicios.ProductoService;
import com.utn.EBS.Servicios.ProductoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/productos")
public class ProductoController extends BaseControllerImpl<Producto, ProductoServiceImpl>{

    @Autowired
    ProductoServiceImpl productoService;
@GetMapping("/buscarPorDenominacion")
public ResponseEntity<?> buscarPorDenominacion(@RequestParam String denominacion){
    try {
        return  ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorDenominacion(denominacion));
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
    }
}
    @GetMapping("/buscarPorTipoProducto")
    public ResponseEntity<?> buscarPorTipoProducto(@RequestParam TipoProducto tipoProducto){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorTipoProducto(tipoProducto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/buscarPorDenominacionPage")
    public ResponseEntity<?> buscarPorDenominacion(@RequestParam String denominacion, Pageable pageable){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorDenominacion(denominacion, pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/buscarPorTipoProductoPage")
    public ResponseEntity<?> buscarPorTipoProducto(@RequestParam TipoProducto tipoProducto, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorTipoProducto(tipoProducto, pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping("/aReponer")
    public ResponseEntity<?> ProductosAReponer() throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body((productoService.ProductosAReponer()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \""+e.getMessage()+"\"}"));
        }
    }
    @GetMapping("/aReponerPaged")
    public ResponseEntity<?> productosAReponerPaged(Pageable pageable) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body((productoService.ProductosAReponer(pageable)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \""+e.getMessage()+"\"}"));
        }
    }

    @PostMapping("/agregarProducto")
    public ResponseEntity<?> agregarProducto(@RequestBody AgregarProductoDTO agregarProductoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.agregarProducto(agregarProductoDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
