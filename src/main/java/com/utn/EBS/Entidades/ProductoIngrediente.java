package com.utn.EBS.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.utn.EBS.Enumeraciones.TipoProducto;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import java.awt.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="producto_ingrediente")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE producto_ingrediente SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")


public class ProductoIngrediente extends BaseEntidad{

    @JsonBackReference(value = "producto-producto-ingrediente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", referencedColumnName = "id")
    private Producto producto;

    @JsonBackReference(value = "ingrediente-producto-ingrediente")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingrediente_id", referencedColumnName = "id")
    private Ingrediente ingrediente;

    @Column(name = "cantidad")
    private int cantidad;
}
