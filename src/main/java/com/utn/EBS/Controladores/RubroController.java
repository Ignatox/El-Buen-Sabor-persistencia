package com.utn.EBS.Controladores;

import com.utn.EBS.Entidades.Rubro;
import com.utn.EBS.Servicios.RubroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/rubros")
public class RubroController extends BaseControllerImpl<Rubro, RubroServiceImpl>{

    @Autowired
    private RubroServiceImpl rubroServiceImpl;
    @GetMapping("/Denominacion")
    public ResponseEntity<?> buscarRubrosPorDenominacion(String denominacion) throws Exception{
        try {
            List<Rubro> rubrosPorDenominacion = rubroServiceImpl.buscarPorDenominacion(denominacion);
            return ResponseEntity.status(HttpStatus.OK).body(rubrosPorDenominacion);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Intente despues.\"}");
        }
    }
}
