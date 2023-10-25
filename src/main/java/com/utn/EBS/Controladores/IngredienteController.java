package com.utn.EBS.Controladores;
import com.utn.EBS.Entidades.Factura;
import com.utn.EBS.Entidades.Ingrediente;
import com.utn.EBS.Repositorios.IngredienteRepository;
import com.utn.EBS.Servicios.FacturaServiceImpl;
import com.utn.EBS.Servicios.IngredienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/ingredientes")
public class IngredienteController extends BaseControllerImpl<Factura, FacturaServiceImpl>{


    @Autowired
    IngredienteServiceImpl ingredienteService;
    @PostMapping("/{id}/registrarCompra")
    public ResponseEntity registrarCompraIngrediente(@PathVariable Long id, @RequestParam double costoCompra) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredienteService.registrarCompraIngrediente(id,costoCompra));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, intente denuevo");
        }
    }

    @PostMapping("/{id}/registrarCompra")
    public ResponseEntity actualizarStockCompraIngrediente(@PathVariable Long id, @RequestParam int cantComprada) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredienteService.actualizarStockCompraIngrediente(id,cantComprada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, intente denuevo");

        }
    }

}