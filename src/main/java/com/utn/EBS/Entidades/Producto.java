package com.utn.EBS.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "tiempo_estimado_cocina")
    @NotNull
    @NotEmpty
    private int tiempoEstimadoCocina;
    @Column(name = "denominacion")
    @NotNull
    @NotEmpty
    private String denominacion;
    @Column(name = "precio_venta")
    @NotNull
    @NotEmpty
    private double precioVenta;
    @Column(name = "precio_compra")
    @NotNull
    @NotEmpty
    private double precioCompra;
    @Column(name = "stock_actual")
    @NotNull
    @NotEmpty
    private int stockAtual;
    @Column(name = "stock_minimo")
    @NotNull
    @NotEmpty
    private int stockMinimo;
    @Column(name = "unidad_medida")
    @NotNull
    @NotEmpty
    private String unidadMedida;
    @Column(name = "foto")
    private String foto;
    @Column(name = "receta")
    @NotNull
    @NotEmpty
    private String receta;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    @NotNull
    @NotEmpty
    private TipoProducto tipoProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @NotNull
    @NotEmpty
    private Rubro rubro;

}