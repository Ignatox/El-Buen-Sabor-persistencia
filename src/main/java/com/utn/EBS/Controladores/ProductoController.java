package com.utn.EBS.Controladores;

import com.utn.EBS.Entidades.Producto;
import com.utn.EBS.Servicios.ProductoServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/productos")

public class ProductoController extends BaseControllerImpl<Producto, ProductoServiceImpl>{
    @GetMapping("/aReponer")
    public ResponseEntity<?> ProductosAReponer(@RequestParam int unidades){
        try{
            return ResponseEntity.status(HttpStatus.OK).body((servicio.ProductosAReponer(unidades)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \""+e.getMessage()+"\"}"));
        }
    }
    @GetMapping("/aReponerPaged")
    public ResponseEntity<?> search(@RequestParam int unidades, Pageable pageable){
        try {
            return ResponseEntity.status(HttpStatus.OK).body((servicio.ProductosAReponer(unidades,pageable)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \""+e.getMessage()+"\"}"));
        }
    }
}
