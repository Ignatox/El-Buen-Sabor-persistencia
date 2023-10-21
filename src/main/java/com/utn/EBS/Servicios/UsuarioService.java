package com.utn.EBS.Servicios;

import com.utn.EBS.DTO.AuthResponse;
import com.utn.EBS.DTO.LoginDTO;
import com.utn.EBS.DTO.RegistroClienteDTO;
import com.utn.EBS.Entidades.Usuario;

import java.util.Optional;

public interface UsuarioService extends BaseService<Usuario, Long>{

    AuthResponse registrarUsuario(Usuario nuevoUsuario) throws Exception;
    AuthResponse login(LoginDTO loginDTO) throws Exception;
}
