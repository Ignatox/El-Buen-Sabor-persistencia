package com.utn.EBS.Entidades;

import com.utn.EBS.Enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="factura")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Factura extends BaseEntidad {

    @Column (name = "nro_factura")
    private int numero;

    @Column (name = "fecha_factura")
    private Date fecha;

    @Column (name = "dto_factura")
    private double descuento;

    @Enumerated(EnumType.STRING)
    @Column (name = "forma_pago")
    private FormaPago formaPago;

    @Column (name = "total")
    private int total;
}
