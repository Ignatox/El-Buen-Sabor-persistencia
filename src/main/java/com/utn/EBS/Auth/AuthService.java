package com.utn.EBS.Auth;


import com.utn.EBS.Entidades.Cliente;
import com.utn.EBS.Entidades.Empleado;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Enumeraciones.RolUsuario;
import com.utn.EBS.JWT.JwtService;
import com.utn.EBS.Repositorios.ClienteRepository;
import com.utn.EBS.Repositorios.EmpleadoRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final ClienteRepository clienteRepository;
    @Autowired
    private final EmpleadoRepository empleadoRepository;
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        System.out.println("Autenticado");
        UserDetails user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        System.out.println("Si existe");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())); //springsecurity

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Autenticado22");
        String token=jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();

    }

    public AuthResponse registerCliente(RegisterClienteRequest request) {

        Cliente cliente = Cliente.builder()
                .email(request.getEmail())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .nombre(request.getNombre())
                //Faltan Domcilios
                .build();

        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(RolUsuario.CLIENTE)
                .build();



        cliente.setUsuario(user);
        clienteRepository.save(cliente);

        /*
        user.setCliente(cliente);
*/
        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

//    public AuthResponse registerEmpleado(RegisterEmpleadoRequest request) {
//
//        Empleado empleado = Empleado.builder()                 //Relacion 1 a 1 con usuario, puede q no vaya
//                .nombre(request.getNombre())
//                .apellido(request.getApellido())
//                .telefono(request.getTelefono())
//                .email(request.getEmail())
//                .rolEmpleado(request.getRol())
//                //Domicilios
//                .build();
//
//        Usuario user = Usuario.builder()
//                .username(request.getUsername())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .rol(RolUsuario.EMPLEADO)
//                .build();
//
//
//
//        empleado.setUsuario(user);
//        empleadoRepository.save(empleado);
//        usuarioRepository.save(user);
//
//
//        return AuthResponse.builder()
//                .token(jwtService.getToken(user))
//                .build();
//    }
}

