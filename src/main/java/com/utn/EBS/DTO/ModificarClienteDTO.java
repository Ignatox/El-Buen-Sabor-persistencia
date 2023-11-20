package com.utn.EBS.DTO;

import com.utn.EBS.Entidades.Domicilio;
import com.utn.EBS.Entidades.Pedido;
import com.utn.EBS.Entidades.Usuario;
import com.utn.EBS.Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ModificarClienteDTO {
    Long idCliente;
    String nombre;
    String apellido;
    String telefono;
    String email;
    String usuario;
    String rol = "Cliente";
}
