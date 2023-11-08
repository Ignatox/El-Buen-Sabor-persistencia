package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.AgregarProductoDTO;
import com.utn.EBS.DTO.AgregarRubroDTO;
import com.utn.EBS.DTO.AgregarRubroDTO;
import com.utn.EBS.DTO.AltaRubroDTO;
import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Servicios.RubroService;
import com.utn.EBS.Servicios.RubroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/rubros")
public class RubroController extends BaseControllerImpl<Rubro, RubroServiceImpl>{

    @Autowired
    private RubroServiceImpl rubroServiceImpl;
    @GetMapping("/Nombre")
    public ResponseEntity<?> buscarRubrosPorNombre(String nombre) throws Exception{
        try {
            List<Rubro> rubrosPorNombre = rubroServiceImpl.buscarPorNombre(nombre);
            return ResponseEntity.status(HttpStatus.OK).body(rubrosPorNombre);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente despues.\"}");
        }
    }

    @Autowired
    RubroServiceImpl rubroService;
    @PostMapping("/agregarRubro")
    public ResponseEntity<?> agregarRubro(@RequestBody AgregarRubroDTO agregarRubroDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rubroService.agregarRubro(agregarRubroDTO));
    @PostMapping("/agregarRubroIngrediente")
    public ResponseEntity<?> agregarRubroIng(@RequestBody Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(rubroService.agregarRubroIng(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/modificarRubros")
    public ResponseEntity<?> editarRubro(AltaRubroDTO altaRubroDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.editarRubro(altaRubroDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
