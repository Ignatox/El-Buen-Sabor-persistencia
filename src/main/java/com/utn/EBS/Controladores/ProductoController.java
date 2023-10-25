package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Servicios.ProductoServiceImpl;
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
