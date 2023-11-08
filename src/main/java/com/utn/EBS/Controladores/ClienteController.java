package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.ClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Servicios.ClienteServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/clientes")

public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl>{

    @GetMapping("/verDatos")
    public ResponseEntity<?> verDatosCliente(@RequestBody Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.datosCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/modificarDatos")      //Esto creo q es un @PostMapping pero no se
    public ResponseEntity<?> modificarDatosCliente(ClienteDTO clienteDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificardatos(clienteDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
