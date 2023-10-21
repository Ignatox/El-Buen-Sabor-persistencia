package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AuthResponse;
import com.utn.EBS.DTO.LoginDTO;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import com.utn.EBS.Utils.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtService jwtService;

    @Override
    @Transactional
    public AuthResponse registrarUsuario(Usuario nuevoUsuario) {
        usuarioRepository.save(nuevoUsuario);
        System.out.println(nuevoUsuario.getApellido());
        AuthResponse auth = AuthResponse.builder()
                .token(jwtService.getToken(nuevoUsuario))
                .build();
        System.out.println(auth.getToken());
        return auth;
    }

    @Override
    @Transactional
    public AuthResponse login(LoginDTO loginDTO) throws Exception {
        try{
            Optional<Usuario> usuario = usuarioRepository.findByEmail(loginDTO.getEmail());
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }
}
