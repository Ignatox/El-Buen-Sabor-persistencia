package desarrollo.sprint5.apiresttest.Auth;

import desarrollo.sprint5.apiresttest.Entity.Cliente;
import desarrollo.sprint5.apiresttest.Entity.Domicilio;
import desarrollo.sprint5.apiresttest.Entity.Localidad;
import desarrollo.sprint5.apiresttest.Entity.Usuario;
import desarrollo.sprint5.apiresttest.Enumeration.EstadoCliente;
import desarrollo.sprint5.apiresttest.Enumeration.Role;
import desarrollo.sprint5.apiresttest.Jwt.JwtService;
import desarrollo.sprint5.apiresttest.Repository.LocalidadRepository;
import desarrollo.sprint5.apiresttest.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    LocalidadRepository localidadRepository;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {

        //Debio a esto ahora siempre hay que enviar localidad
        //Localidad localidad = localidadRepository.getReferenceById(request.getIdlocalidad());

        Domicilio domicilio = Domicilio.builder()
                .calle(request.getCalle())
                .nroCalle(request.getNroCalle())
                .pisoDpto(request.getPisoDpto())
                .nroDpto(request.getNroDpto())
                //.localidad(localidad)
                .build();

        Cliente cliente = Cliente.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .mail(request.getMail())
                .fechaHoraAltaCliente(LocalDate.now())
                .estadoCliente(EstadoCliente.ALTA)
                .build();

        cliente.agregarDomicilio(domicilio);

        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fechaAltaUsuario(LocalDate.now())
                .role(Role.CLIENTE)
                .build();

        user.setCliente(cliente);
        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

    public AuthResponse registerEmployee(RegisterEmployeeRequest request) {

        Localidad localidad = localidadRepository.getOne(request.idlocalidad);

        Domicilio domicilio = Domicilio.builder()
                .calle(request.calle)
                .nroCalle(request.nroCalle)
                .pisoDpto(request.pisoDpto)
                .nroDpto(request.nroDpto)
                .localidad(localidad)
                .fechaHoraAltaDomicilio(LocalDate.now())
                .build();

        Cliente cliente = Cliente.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .telefono(request.getTelefono())
                .mail(request.getMail())
                .fechaHoraAltaCliente(LocalDate.now())
                .estadoCliente(EstadoCliente.ALTA)
                .build();

        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.provisionalPassword))
                .fechaAltaUsuario(LocalDate.now())
                .role(Role.fromValorNumerico(request.getIdRole())) //ver numeracion de roles
                .build();

        user.setCliente(cliente);
        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }

}