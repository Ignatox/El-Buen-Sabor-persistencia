package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.Rol;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="usuario")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE usuario SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class Usuario extends  BaseEntidad {

    @Column(name="nombre")
    @NotNull
    @NotEmpty
    private String nombre;
    @Column(name="contraseña")
    @NotNull
    @NotEmpty
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name="rol")
    @NotNull
    @NotEmpty
    private Rol rol;

}
