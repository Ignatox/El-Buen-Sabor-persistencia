package com.utn.EBS.Auth;


import com.utn.EBS.Entidades.Domicilio;
import com.utn.EBS.Enumeraciones.RolEmpleado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterEmpleadoRequest {
    String username;
    String nombre;
    String apellido;
    String telefono;
    String email;
    List<Domicilio> domicilios;
    String password;
    RolEmpleado rol;
}
