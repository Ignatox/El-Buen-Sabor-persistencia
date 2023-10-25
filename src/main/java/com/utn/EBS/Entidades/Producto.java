package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.TipoProducto;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="producto")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE producto SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")


public class Producto extends BaseEntidad {

    @Column(name = "tiempo_estimado_cocina", nullable = false)

    private int tiempoEstimadoCocina;
    @Column(name = "denominacion", nullable = false)


    private String denominacion;
    @Column(name = "precio_venta", nullable = false)

    private double precioVenta;
    @Column(name = "precio_compra", nullable = false)

    private double precioCompra;
    @Column(name = "stock_actual", nullable = false)

    private int stockAtual;
    @Column(name = "stock_minimo", nullable = false)

    private int stockMinimo;
    @Column(name = "unidad_medida", nullable = false)

    private String unidadmedida;
    @Column(name = "foto")
    private String foto;
    @Column(name = "receta", nullable = false)

    private String receta;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)

    private TipoProducto tipoProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    private Rubro rubro;

}