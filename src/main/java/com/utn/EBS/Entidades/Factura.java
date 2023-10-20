package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.FormaPago;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;

@Entity
@Table(name="factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
//Usamos la siguiente etiqueta para hacer un update de sql al atributo "DELETED" a TRUE
@SQLDelete(sql = "UPDATE factura SET deleted = true WHERE id=?")
//Siempre que busquemos entidades, no van a hacer incluidas las que tengan su atributo deleted= true
@Where(clause = "deleted=false")
public class Factura extends BaseEntidad {

    @Column (name = "nro_factura")
    @NotNull
    @NotEmpty
    private int numero;

    @Column (name = "fecha_factura")
    @NotNull
    @NotEmpty
    private Date fecha;

    @Column (name = "dto_factura")
    @NotNull
    @NotEmpty
    private double descuento;

    @Enumerated(EnumType.STRING)
    @Column (name = "forma_pago")
    @NotNull
    @NotEmpty
    private FormaPago formaPago;

    @Column (name = "total")
    @NotNull
    @NotEmpty
    private int total;
}
