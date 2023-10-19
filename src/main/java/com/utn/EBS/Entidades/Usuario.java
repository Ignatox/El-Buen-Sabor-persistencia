package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="usuario")
public class Usuario extends  BaseEntidad {

    @Column(name="nombre")
    private String nombre;
    @Column(name="contraseña")
    private String password;
    @Column(name="rol")
    private Rol rol;

}
