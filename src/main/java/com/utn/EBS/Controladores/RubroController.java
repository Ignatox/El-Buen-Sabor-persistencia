package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.*;
import com.utn.EBS.DTO.AgregarRubroDTO;
;
import com.utn.EBS.Entidades.Rubro;

import com.utn.EBS.Servicios.RubroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/rubros")
public class RubroController extends BaseControllerImpl<Rubro, RubroServiceImpl> {

    @Autowired
    private RubroServiceImpl rubroServiceImpl;

    @GetMapping("/Nombre")
    public ResponseEntity<?> buscarRubrosPorNombre(String nombre) throws Exception {
        try {
            List<Rubro> rubrosPorNombre = rubroServiceImpl.buscarPorNombre(nombre);
            return ResponseEntity.status(HttpStatus.OK).body(rubrosPorNombre);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente despues.\"}");
        }
    }

    @PostMapping("/agregarRubro")
    public ResponseEntity<?> agregarRubro(@RequestBody AgregarRubroDTO agregarRubroDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rubroServiceImpl.agregarRubro(agregarRubroDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente despues.\"}");
        }
    }

    @PutMapping("/modificarRubro/{id}")
    public ResponseEntity<?> editarRubro(@PathVariable Long id,@RequestBody AgregarRubroDTO agregarRubroDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.editarRubro(id,agregarRubroDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





}
