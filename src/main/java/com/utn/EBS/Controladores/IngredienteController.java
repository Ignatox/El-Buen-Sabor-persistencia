package com.utn.EBS.Controladores;
import com.utn.EBS.Entidades.Ingrediente;
import com.utn.EBS.Repositorios.IngredienteRepository;
import com.utn.EBS.Servicios.IngredienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/ingredientes")
public class IngredienteController extends BaseControllerImpl<Ingrediente, IngredienteServiceImpl>{


    @Autowired
    IngredienteServiceImpl ingredienteService;
    @PostMapping("/{id}/registrarCompra")
    public ResponseEntity<?> registrarCompraIngrediente(@PathVariable Long id, @RequestParam double costoCompra) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredienteService.registrarCompraIngrediente(id,costoCompra));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, intente denuevo");
        }
    }

    @PostMapping("/{id}/actualizarStockIngrediente")
    public ResponseEntity<?> actualizarStockIngrediente(@PathVariable Long id, @RequestParam int cantComprada) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(ingredienteService.actualizarStockIngrediente(id,cantComprada));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, intente denuevo");

        }
    }
    @GetMapping("/buscarPorStockOK")
    public ResponseEntity<?> buscarPorStockOK(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorStockOK());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\""+e.getMessage()+"\"}");
        }
    }
    @GetMapping("/buscarPorStocNoOK")
    public ResponseEntity<?> buscarPorStockNoOK() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorStockNoOK());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    @GetMapping("/buscarPorDenominacion")
    public ResponseEntity<?> buscarPorDenominacion(@RequestParam String filtroDenom){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(servicio.buscarPorDenominacion(filtroDenom));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}