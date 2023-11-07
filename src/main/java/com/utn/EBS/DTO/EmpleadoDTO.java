package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.Domicilio;
import com.utn.EBS.Enumeraciones.Rol;
import lombok.Data;

import java.util.List;

@Data
public class EmpleadoDTO {
    //nombre, apellido, dirección, departamento, teléfono, 
    //email y una clave provisoria, la cual el empleado, al tener acceso por primera vez, tendrá que modificar obligatoriamente.
    //SeleccionarROL
    //que no exista un empleado registrado con la misma direccion de email
    Long idCliente;
    String nombre;
    String apellido;
    String telefono;
    String email;
    List<Domicilio> domicilio;
    String contrasena;
    Rol rol;





}
