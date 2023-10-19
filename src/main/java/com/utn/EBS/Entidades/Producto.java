package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.TipoProducto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="producto")

public class Producto extends BaseEntidad {

    @Column(name = "tiempo_estimado_cocina")
    private int tiempoEstimadoCocina;
    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "precio_venta")
    private double precioVenta;
    @Column(name = "precio_compra")
    private double precioCompra;
    @Column(name = "stock_actual")
    private int stockAtual;
    @Column(name = "stock_minimo")
    private int stockMinimo;
    @Column(name = "unidad_medida")
    private String unidadmedida;
    @Column(name = "foto")
    private String foto;
    @Column(name = "receta")
    private String receta;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoProducto tipoProducto;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id", nullable = false)
    private Rubro rubro;

}