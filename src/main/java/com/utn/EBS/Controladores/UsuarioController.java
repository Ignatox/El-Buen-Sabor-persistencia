package com.utn.EBS.Controladores;

import com.utn.EBS.DTO.AuthResponse;
import com.utn.EBS.DTO.LoginDTO;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Servicios.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "auth/api/v1/usuarios")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioServiceImpl>{

    @Autowired
    UsuarioServiceImpl usuarioService;

    // Ruta para registrar un nuevo usuario
    @PostMapping(path = "/crearUsuario")
    public ResponseEntity<AuthResponse> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.registrarUsuario(nuevoUsuario));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse());
        }
    }

    @RequestMapping("/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDTO loginDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.login(loginDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AuthResponse());
        }
    }
}
