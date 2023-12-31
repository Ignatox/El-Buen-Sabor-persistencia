package com.utn.EBS.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterClienteRequest {
    String nombre;
    String password;
    String apellido;
    String username;
    String telefono;
    String email;
    //List<Domicilio> domicilios;
}
