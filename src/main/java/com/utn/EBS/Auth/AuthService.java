package com.utn.EBS.Auth;

import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Enumeraciones.Rol;
import com.utn.EBS.JWT.JwtService;
import com.utn.EBS.Repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /*
    Hay que hacer cuantos sean necesarios, para cada rol
    Aunque ya no se jajajaj
    */
    public AuthResponse login(LoginRequest request) { //Metodo para loguearse
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (
                request.getNombre(),
                request.getPassword()      //Son los get de los DTO, revisar bien (Ahora estan matcheados con LoginRequest)
                ));
        UserDetails userDetails = usuarioRepository.BuscarPorNombreUsuario(
                request.getNombre()
        ).orElseThrow();
        String token = jwtService.getToken(userDetails);
        return AuthResponse.builder()  //Nuevo DTO, se envia como respuesta para que el cliente almacene el token y no tenga q loguearse cada vez q inica la pag
                .token(token)
                .build();
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
