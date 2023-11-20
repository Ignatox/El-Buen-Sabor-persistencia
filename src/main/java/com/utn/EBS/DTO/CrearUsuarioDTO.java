package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.RolUsuario;
import lombok.Data;

@Data
public class CrearUsuarioDTO {
    String username;
    String password;
    RolUsuario rol;
}
