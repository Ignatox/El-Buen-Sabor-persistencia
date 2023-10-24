package com.utn.EBS.Entidades;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "denominacion")
    @NotNull
    @NotEmpty
    private String denominacion;
    @Column(name = "fecha-modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date fecha_modificacion;
    @Column(name = "precio-compra")
    @NotNull
    @NotEmpty
    private float costo;
    @Column(name = "stok-actual")
    @NotNull
    @NotEmpty
    private int stockActual;
    @Column(name = "stock-minimo")
    @NotNull
    @NotEmpty
    private int stockMinimo;
    @Column(name = "url-imagen")
    private String urlImagen;
}
