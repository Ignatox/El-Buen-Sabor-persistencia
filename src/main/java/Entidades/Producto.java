package Entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="pedido")

public class Producto extends BaseEntidad {
    @Column(name = "tiempoestimadococina")
    private int tiempoEstimadoCocina;
    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "precioventa")
    private double precioVenta;
    @Column(name = "preciocompra")
    private double precioCompra;
    @Column(name = "stockactual")
    private int stockAtual;
    @Column(name = "stockminimo")
    private int stockMinimo;
    @Column(name = "unidadmedida")
    private String unidadmedida;
    @Column(name = "foto")
    private String foto;
    @Column(name = "receta")
    private String receta;

}