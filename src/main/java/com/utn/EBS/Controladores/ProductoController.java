package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.DTO.BuscarRankingProductosDTO;
import com.utn.EBS.Entidades.Producto;
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

    @PostMapping("/agregarProducto")
    public ResponseEntity<?> agregarProducto(@RequestBody AgregarProductoDTO agregarProductoDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.agregarProducto(agregarProductoDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Habria que hacer un update de producto, o sino usar el base.
    @GetMapping(path = "/paginaPrincipal")
    public ResponseEntity<?> traerProductosPaginaPrincipal() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.traerProductosPaginaPrincipal());
        } catch (Exception e) {
            System.out.println("Error algo malo paso: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

@GetMapping("/buscarPorNombre")
public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre){
    try {
        return  ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorNombre(nombre));
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
    }
}


    @GetMapping("/buscarPorNombrePage")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre, Pageable pageable){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorNombre(nombre, pageable));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @GetMapping(path = "/rankingProductos")
    public ResponseEntity<?> traerRankingProductos(@RequestBody BuscarRankingProductosDTO buscarRankingProductosDTO){
        try {
            return  ResponseEntity.status(HttpStatus.OK).body(productoService.traerRankingProductos(buscarRankingProductosDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    // queda comentado porque la busqueda deberia ser por rubro, y no por tipoProducto

//    @GetMapping("/buscarPorTipoProducto")
//    public ResponseEntity<?> buscarPorTipoProducto(@RequestParam TipoProducto tipoProducto){
//        try {
//            return  ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorTipoProducto(tipoProducto));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
//        }
//    }
//    @GetMapping("/buscarPorTipoProductoPage")
//    public ResponseEntity<?> buscarPorTipoProducto(@RequestParam TipoProducto tipoProducto, Pageable pageable) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(productoService.buscarPorTipoProducto(tipoProducto, pageable));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\":\"" + e.getMessage() + "\"}"));
//        }
//    }
}
