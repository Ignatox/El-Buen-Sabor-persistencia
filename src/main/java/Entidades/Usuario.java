package Entidades;

import Enumeraciones.Rol;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario extends  BaseEntidad {
    private String nombre;
    private String password;
    private Rol rol;


}
