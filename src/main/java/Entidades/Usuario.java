package Entidades;

import Enumeraciones.Rol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Usuario")
public class Usuario extends  BaseEntidad {
    @Column(name="nombre")
    private String nombre;
    @Column(name="contraseña")
    private String password;
    @Column(name="Rol")
    private Rol rol;


}
