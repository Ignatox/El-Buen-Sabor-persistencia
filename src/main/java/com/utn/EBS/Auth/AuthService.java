package com.utn.EBS.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    public AuthResponse login(LoginRequest request) { //Metodo para loguearse
        return null;
    }

    public AuthResponse registrar(RegistrarRequest request) { //Metodo para registrarse
        return null;
    }
}
