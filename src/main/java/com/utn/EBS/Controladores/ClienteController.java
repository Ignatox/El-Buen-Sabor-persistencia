package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.RegistroClienteDTO;
import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Enumeraciones.Rol;
import com.utn.EBS.Servicios.ClienteServiceImpl;
import com.utn.EBS.Servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "auth/api/v1/clientes")

public class ClienteController extends BaseControllerImpl<Cliente, ClienteServiceImpl>{
    @Autowired
    ClienteServiceImpl clienteService;
    @PostMapping(path = "/registrarCliente")
    public ResponseEntity<?> registrarCliente(@RequestBody RegistroClienteDTO registroClienteDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(clienteService.registrarCliente(registroClienteDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pudo registrarse el cliente");
        }
    }
}
