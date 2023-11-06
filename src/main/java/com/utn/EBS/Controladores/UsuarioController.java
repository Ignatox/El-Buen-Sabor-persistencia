package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.ModificarClienteDTO;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl>{
    @Autowired
    UsuarioServiceImpl usuarioService;
    @GetMapping("/mostrarclientes")
    public ResponseEntity<?> mostrarClientes(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.mostrarClientes());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("/modificarCliente")
    public ResponseEntity<?> modificarCliente(ModificarClienteDTO clienteDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.modificarCliente(clienteDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente mas tarde.\"}");
        }
    }
}
