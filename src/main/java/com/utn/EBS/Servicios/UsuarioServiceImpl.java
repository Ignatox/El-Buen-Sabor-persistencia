package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.ClienteDomicilioDTO;
import com.utn.EBS.DTO.CrearUsuarioDTO;
import com.utn.EBS.DTO.ModificarClienteDTO;
import com.utn.EBS.Entidades.*;
import com.utn.EBS.Enumeraciones.EstadoPedido;
import com.utn.EBS.Repositorios.BaseRepository;
import com.utn.EBS.Repositorios.UsuarioRepository;
import com.utn.EBS.Repositorios.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl extends BaseServiceImpl<Usuario, Long> implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario crearUsuario(CrearUsuarioDTO crearUsuarioDTO) throws Exception {
        Usuario nuevoUsuario = Usuario.builder()
                .username(crearUsuarioDTO.getUsername())
                .password(crearUsuarioDTO.getPassword())
                .role(crearUsuarioDTO.getRol())
                .build();
        Usuario usuario1 = usuarioRepository.save(nuevoUsuario);
        return usuario1;
    }

    public UsuarioServiceImpl(BaseRepository<Usuario, Long> baseRepository) {
        super(baseRepository);
    }


}

