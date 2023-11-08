package com.utn.EBS.Entidades;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.utn.EBS.Enumeraciones.EstadoRubro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rubro")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE rubro SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class Rubro extends  BaseEntidad{

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name= "estado", nullable = false)
    private EstadoRubro estado;

    @JsonManagedReference(value = "rubro-producto")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rubro" , orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Producto> productos = new ArrayList<>();
}
