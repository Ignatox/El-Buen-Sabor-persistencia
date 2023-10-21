package com.utn.EBS.DTO;

import com.utn.EBS.Enumeraciones.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroClienteDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String telefono;
}