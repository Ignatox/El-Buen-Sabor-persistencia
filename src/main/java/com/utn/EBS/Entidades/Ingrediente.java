package com.utn.EBS.Entidades;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ingrediente")
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE ingrediente SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")

public class Ingrediente extends BaseEntidad{
    @Column(name = "denominacion", nullable = false)

    private String denominacion;

    @Column(name = "precio-compra", nullable = false)
    private float costo;
    @Column(name = "stok-actual", nullable = false)
    private int stockActual;
    @Column(name = "stock-minimo", nullable = false)
    private int stockMinimo;
    @Column(name = "url-imagen")
    private String urlImagen;
}
