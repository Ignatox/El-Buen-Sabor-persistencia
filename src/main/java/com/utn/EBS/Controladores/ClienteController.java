package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.ModificarClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Servicios.ClienteServiceImpl;
import com.utn.EBS.Servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    ClienteServiceImpl service;
    @GetMapping("/mostrarclientes")
    public ResponseEntity<?> mostrarClientes(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.mostrarClientes());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/modificarCliente")
    public ResponseEntity<?> modificarCliente(ModificarClienteDTO clienteDTO, Cliente cliente){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.modificarCliente(clienteDTO, cliente));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/verDatos")
    public ResponseEntity<?> verDatosCliente(@RequestBody Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.datosCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/modificarDatos")      //Esto creo q es un @PostMapping pero no se
    public ResponseEntity<?> modificarDatosCliente(@RequestBody ClienteDTO clienteDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificardatos(clienteDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



}
