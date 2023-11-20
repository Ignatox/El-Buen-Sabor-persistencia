package com.utn.EBS.Auth;

import com.utn.EBS.DTO.CrearUsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor        //Obliga al constructor a pedir todos los argumentos
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/login") //no puedo mandar un body con get
    public ResponseEntity<?> login(@RequestBody LoginRequest request)
    {
        try {
            return ResponseEntity.ok(authService.login(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("No pudo autenticarse");
        }

    }

    @PostMapping(value = "registerCliente")
    public ResponseEntity<?> registerCliente(@RequestBody RegisterClienteRequest request)
    {
        try {
            return ResponseEntity.ok(authService.registerCliente(request));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(value = "crearUsuario")
    public ResponseEntity<?> crearUsuario(@RequestBody CrearUsuarioDTO crearUsuarioDTO)
    {
        return ResponseEntity.ok(authService.crearUsuario(crearUsuarioDTO));
    }

}

