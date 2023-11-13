package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="usuario", uniqueConstraints ={@UniqueConstraint(columnNames = {"nombre"})}) // IMPORTANTE: Es un constraint para que no se repitan los usernames en la base de datos
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE usuario SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class Usuario extends  BaseEntidad implements UserDetails {

    /*
    * IMPORTANTISIMO: Usuario deberia extenderse tanto a Empleado como a Cliente
    * Aca se modela la entidad que va a matchear con todos los datos q se carguen en la pagina
    * para registrarse
    *
    * Si les pinta a los que estan laburando aca Eliminar Cliente y Empleado, usar solamente una entidad
    * y elegirle un rol para diferenciar
    *
    * Si no como puse recien, extender esta clase a ambos y luego cargar los datos necesarios de cada uno,
    * pero habria q ver la diferencia entre ambos y como se conecta la pagina con el controller y el controller a cada service correspondiente
    * para probar con las entidades
    *
    *
    */

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="contraseña", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name="rol", nullable = false)
    private Rol rol;

    //Puede q haya q cambiar los return de cada funcion
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(rol.name())); //puede q no se name
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
