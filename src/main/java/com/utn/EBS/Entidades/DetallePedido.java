package com.utn.EBS.Entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="detalle_pedido")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE detalle_pedido SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class DetallePedido extends BaseEntidad{

    @Column(name = "cantidad", nullable = false)
    private int cantidad;
    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    @JsonBackReference(value = "pedido-detalle-pedido")
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    //@JsonBackReference(value = "producto-detalle-pedido")
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
