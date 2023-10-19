package com.utn.EBS.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="rubro")
public class Rubro extends  BaseEntidad{

    @Column(name = "denominacionRubro")
    private String denominacion;

    /* RELACIÓN CON PRODUCTO*/
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name="rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();
    /*METODOS PARA AGREGAR Y MOSTRAR PRODUCTOS DE LA LISTA*/
    public void agregarProducto(Producto ped){
        productos.add(ped);
    }
    public void mostrarProductos() {
        System.out.println("Productos de " + Rubro.this);
        for (Producto producto : productos) {
            System.out.println("Tipo: " + producto.getTipoProducto() +", Tiempo Estimado de Cosina: " + producto.getTiempoEstimadoCocina());
            System.out.println("Denominación: "+ producto.getDenominacion()+ ", Precio de Venta: "+producto.getPrecioVenta()+", Precio de Compra: "+producto.getPrecioCompra());
            System.out.println("Stock Actual: "+ producto.getStockAtual()+", Stock Mínimo: "+ producto.getStockMinimo());
            System.out.println("Unidad de Medida: "+ producto.getUnidadmedida()+", Receta: "+producto.getReceta());
        }

    }
}
