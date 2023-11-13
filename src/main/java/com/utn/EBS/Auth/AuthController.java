package com.utn.EBS.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")  //Se puede cambiar la ruta de autorizacion por otra si quieren  //Ver si hay q poner api/v1
@RequiredArgsConstructor        //Obliga al constructor a pedir todos los argumentos
public class AuthController {

    private final AuthService authService;
    @PostMapping(value = "/login") //no puedo mandar un body con get //Ojo con la barra
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "registrar")
    public ResponseEntity<AuthResponse> registrar(@RequestBody RegistrarRequest request)
    {
        return ResponseEntity.ok(authService.registrar(request));
    }
}
