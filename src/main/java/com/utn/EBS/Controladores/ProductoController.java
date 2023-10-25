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
    public ResponseEntity<?> ProductosAReponer() throws Exception{
        try{
            return ResponseEntity.status(HttpStatus.OK).body((servicio.ProductosAReponer()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \""+e.getMessage()+"\"}"));
        }
    }
    @GetMapping("/aReponerPaged")
    public ResponseEntity<?> productosAReponerPaged(Pageable pageable) throws Exception{
        try {
            return ResponseEntity.status(HttpStatus.OK).body((servicio.ProductosAReponer(pageable)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(("{\"error\": \""+e.getMessage()+"\"}"));
        }
    }
}
