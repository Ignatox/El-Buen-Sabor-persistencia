package com.utn.EBS.Auth;

import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Enumeraciones.Rol;
import com.utn.EBS.JWT.JwtService;
import com.utn.EBS.Repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;

    /*
    Hay que hacer cuantos sean necesarios, para cada rol
    Aunque ya no se jajajaj
    */
    public AuthResponse login(LoginRequest request) { //Metodo para loguearse
        return null;
    }

    public AuthResponse registrar(RegistrarRequest request) { //Metodo para registrarse
        Usuario usuario = Usuario.builder()
                .nombre(request.
                        //getNombre() //Depende del DTO que se vaya a usar, si Empleado o Cliente
                        )
                .password(request.
                        //getPassword() //Depende del DTO que se vaya a usar, si Empleado o Cliente
                )
                .rol(Rol.
                        //Aca se elige el rol que le vayan a poner
                        )
                .build();

        usuarioRepository.save(usuario);

        return AuthResponse.builder()
                .token(jwtService.getToken(usuario))
                .build();
    }


}
