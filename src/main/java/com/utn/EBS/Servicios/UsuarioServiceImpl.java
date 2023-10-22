package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AuthResponse;
import com.utn.EBS.DTO.LoginDTO;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import com.utn.EBS.Utils.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthResponse registrarUsuario(Usuario nuevoUsuario) throws Exception {
        // hasheamos la contraseña y despues almacenamos el usuario
        String contrasenaHash = passwordEncoder.encode(nuevoUsuario.getPassword());
        nuevoUsuario.setContrasena(contrasenaHash);
        usuarioRepository.save(nuevoUsuario);
        try {
            AuthResponse auth = AuthResponse.builder()
                    .token(jwtService.getToken(nuevoUsuario))
                    .build();
            return auth;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    @Transactional
    public AuthResponse login(LoginDTO loginDTO) throws Exception {
        try{
            Usuario usuario = usuarioRepository.findByEmail(loginDTO.getEmail()).orElseThrow();
            if (!passwordEncoder.matches(loginDTO.getContrasena(), usuario.getPassword())) {
                throw new Exception("La contraseña es invalida");
            }
            String token = jwtService.getToken(usuario);
            return AuthResponse.builder()
                    .token(token)
                    .build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }
}
