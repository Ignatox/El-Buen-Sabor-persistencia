package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.Domicilio;
import com.utn.EBS.Enumeraciones.Rol;
import lombok.Data;

import java.util.List;

@Data
public class ModificarEmpleadoDTO {
    Long IdEmpleado;
    String nombre;
    String apellido;
    String telefono;
    String email;
    List<Domicilio> domicilio;
    String contrasena;
    Rol rol;
}
